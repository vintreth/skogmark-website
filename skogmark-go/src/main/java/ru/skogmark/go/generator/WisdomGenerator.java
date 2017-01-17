package ru.skogmark.go.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skogmark.go.dao.ConjunctionDao;
import ru.skogmark.go.dao.SentencePartDao;
import ru.skogmark.go.domain.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author svip
 *         2016-11-26
 */
@Component
@Scope("session")
public class WisdomGenerator {
    private static final String LOCAL_REPOSITORY = "local-wisdom.txt";
    private static final String LOCAL_REPOSITORY_ENCODING = "utf-8";

    private static final int COMMA_SEPARATOR_RATIO = 5;
    private static final int ADDITIONAL_WORDS_RATIO = 3;
    private static final int ADVERBIAL_RATIO = 4;

    private List<String> localRepository;

    private SentencePartDao sentencePartDao;
    private ConjunctionDao conjunctionDao;

    private List<SentencePart> sentenceParts;
    private List<Conjunction> conjunctions;

    @Autowired
    public WisdomGenerator(SentencePartDao sentencePartDao, ConjunctionDao conjunctionDao) {
        this.sentencePartDao = sentencePartDao;
        this.conjunctionDao = conjunctionDao;
    }

    public Wisdom generateOne() {
        if (null == localRepository) {
            loadLocalRepository();
        }
        int rand1 = random(localRepository.size());
        int rand2 = random(localRepository.size());
        Wisdom wisdom = new Wisdom();
        wisdom.setContent(localRepository.get(rand1) + " " + localRepository.get(rand2));

        return wisdom;
    }

    public Wisdom[] generateMany(int count) {
        Wisdom[] wisdoms = new Wisdom[count];
        for (int i = 0; i < count; i++) {
            wisdoms[i] = generateOne();
        }
        return wisdoms;
    }

    private void loadLocalRepository() {
        URL localRepositoryUrl = Thread.currentThread().getContextClassLoader().getResource(LOCAL_REPOSITORY);
        if (null == localRepositoryUrl) {
            throw new FailedGenerationException("Unable to find local repository file " + LOCAL_REPOSITORY);
        }

        try (InputStream inputStream = new FileInputStream(localRepositoryUrl.getFile())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, LOCAL_REPOSITORY_ENCODING));
            localRepository = new ArrayList<>();
            String line;
            while (null != (line = reader.readLine())) {
                localRepository.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new FailedGenerationException("Unable to resolve local repository file " + LOCAL_REPOSITORY, e);
        } catch (IOException e) {
            throw new FailedGenerationException("Failure to read file" + LOCAL_REPOSITORY, e);
        }
    }

    public Wisdom generateOneAdvanced() {
        // todo refactor using sentence templates
        String[] sentenceTemplates = new String[] {
                "complex & complex",
                "complex, complex",
                "complex & compound",
                "complex & compound adverbial",
        };
        parseTemplate(sentenceTemplates[3]);
        GenerationStrategy[] generationStrategies = new GenerationStrategy[] {
                this::generateComplex,
                this::generateCompound,
                // todo generateList
        };
        GenerationStrategy generationStrategy = generationStrategies[random(generationStrategies.length)];
        Wisdom wisdom = generationStrategy.generate();

        if (0 >= random(ADDITIONAL_WORDS_RATIO)) {
            SentencePart additionalPart = pickRandomPart(RoleId.NONE);
            if (additionalPart == null) {
                throw new FailedGenerationException(
                        "Unable to retrieve sentence with role " + RoleId.NONE + " from dao layer");
            }
            wisdom.setContent(wisdom.getContent() + ". " + additionalPart.getContent());
        }

        return wisdom;
    }

    private String parseTemplate(String template) {
        if (0 >= template.length()) {
            throw new FailedGenerationException("Unable to parse empty template");
        }
        String[] templateParts = template.split("\\s");
        String[] filledParts = new String[templateParts.length];
        for (String templatePart : templateParts) {
            if ("&".equals(templatePart)) {
                //todo process conjunction
            } else {
                RoleId roleId = RoleId.valueOf(templatePart.toUpperCase());
                SentencePart sentencePart = pickRandomPart(roleId);
            }
        }
        return String.join(" ", filledParts);
    }

    private Wisdom generateComplex() {
        String firstPart = pickRandomPart(RoleId.COMPLEX).getContent();
        String secondPart = pickRandomPart(RoleId.COMPLEX).getContent();
        //todo avoid duplicates

        Wisdom wisdom = new Wisdom();
        wisdom.setType("complex");
        if (0 < random(COMMA_SEPARATOR_RATIO)) {
            wisdom.setContent(String.format(
                    "%s %s %s", firstPart, pickRandomConjunction(RoleId.COMPLEX).getContent(), secondPart));
        } else {
            wisdom.setContent(String.format("%s, %s", firstPart, secondPart));
        }

        return wisdom;
    }

    private Wisdom generateCompound() {
        String firstPart = pickRandomPart(RoleId.COMPLEX).getContent();
        String secondPart = pickRandomPart(RoleId.COMPOUND).getContent();
        String content = String.format(
                "%s %s %s", firstPart, pickRandomConjunction(RoleId.COMPOUND).getContent(), secondPart);
        if (0 >= random(ADVERBIAL_RATIO)) {
            content += ", " + pickRandomPart(RoleId.ADVERBIAL).getContent();
        }
        Wisdom wisdom = new Wisdom();
        wisdom.setContent(content);
        wisdom.setType("compound");

        return wisdom;
    }

    private int random(int bound) {
        return new Random().nextInt(bound);
    }

    private SentencePart pickRandomPart(RoleId roleId) {
        if (null == sentenceParts) {
            sentenceParts = sentencePartDao.getAll();
            if (null == sentenceParts || sentenceParts.isEmpty()) {
                throw new FailedGenerationException("Unable to retrieve sentences from dao layer");
            }
        }
        SentencePart sentencePart;
        do {
            int randomIndex = random(sentenceParts.size());
            sentencePart = sentenceParts.get(randomIndex);
        } while (roleId.value != sentencePart.getRole().getId());

        return sentencePart;
    }

    private Conjunction pickRandomConjunction(RoleId roleId) {
        if (null == conjunctions) {
            conjunctions = conjunctionDao.getAll();
            if (null == conjunctions || conjunctions.isEmpty()) {
                throw new FailedGenerationException("Unable to retrieve conjunctions from dao layer");
            }
        }
        Conjunction conjunction;
        do {
            int randomIndex = random(conjunctions.size());
            conjunction = conjunctions.get(randomIndex);
        } while (roleId.value != conjunction.getRole().getId());

        return conjunction;
    }
}
