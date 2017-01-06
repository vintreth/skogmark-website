package ru.skogmark.go.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skogmark.go.dao.ConjunctionDao;
import ru.skogmark.go.dao.SentencePartDao;
import ru.skogmark.go.domain.Conjunction;
import ru.skogmark.go.domain.RoleId;
import ru.skogmark.go.domain.SentencePart;
import ru.skogmark.go.domain.Wisdom;

import javax.sql.DataSource;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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

    private List<String> localRepository;

    private SentencePartDao sentencePartDao;
    private ConjunctionDao conjunctionDao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public WisdomGenerator(SentencePartDao sentencePartDao, ConjunctionDao conjunctionDao) {
        this.sentencePartDao = sentencePartDao;
        this.conjunctionDao = conjunctionDao;
    }

    public Wisdom generateOne() {
        if (null == localRepository) {
            loadLocalRepository();
        }
        int rand1 = new Random().nextInt(localRepository.size());
        int rand2 = new Random().nextInt(localRepository.size());
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
        GenerationStrategy[] generationStrategies = new GenerationStrategy[]{
                this::generateComplex,
        };
        GenerationStrategy generationStrategy = generationStrategies[new Random().nextInt(generationStrategies.length)];

        return generationStrategy.generate();
    }

    private Wisdom generateComplex() {
        List<SentencePart> sentenceParts = sentencePartDao.getAllByRoleId(RoleId.COMPLEX);
        if (sentenceParts.isEmpty()) {
            throw new FailedGenerationException("Unable to retrieve sentences from dao layer");
        }
        Conjunction conjunction = conjunctionDao.getRandomByRoleId(RoleId.COMPLEX);
        if (conjunction == null) {
            throw new FailedGenerationException("Unable to retrieve conjunction from dao layer");
        }

        Wisdom wisdom = new Wisdom();
        wisdom.setContent(String.format(
                "%s %s %s",
                sentenceParts.get(new Random().nextInt(sentenceParts.size())).getContent(),
                conjunction.getContent(),
                sentenceParts.get(new Random().nextInt(sentenceParts.size())).getContent()));

        return wisdom;
    }
}
