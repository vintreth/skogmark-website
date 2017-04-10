package ru.skogmark.go.gen.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.dao.SentencePartDao;
import ru.skogmark.go.gen.core.domain.Conjunction;
import ru.skogmark.go.gen.core.domain.RoleBased;
import ru.skogmark.go.gen.core.domain.RoleId;
import ru.skogmark.go.gen.core.domain.SentencePart;
import ru.skogmark.go.gen.core.domain.Wisdom;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author svip
 *         2016-11-26
 */
@Component
@Scope("session")
public class WisdomGenerator {
    private static final String LOCAL_REPOSITORY = "local-wisdom.txt";
    private static final String LOCAL_REPOSITORY_ENCODING = "utf-8";
    private static final int ADDITIONAL_WORDS_RATIO = 3;
    private static final int RANDOM_PICKING_MAX_TRIES_COUNT = 1000;

    private static final Logger logger = Logger.getLogger(WisdomGenerator.class);

    private List<String> localRepository;

    private SentencePartDao sentencePartDao;
    private ConjunctionDao conjunctionDao;

    private EntityCache<SentencePart> sentencePartCache;
    private EntityCache<Conjunction> conjunctionCache;

    @Autowired
    public WisdomGenerator(SentencePartDao sentencePartDao, ConjunctionDao conjunctionDao) {
        this.sentencePartDao = sentencePartDao;
        this.conjunctionDao = conjunctionDao;
    }

    private static class EntityCache<E> {
        private Supplier<List<E>> daoCallSupplier;
        private List<E> cachedEntities;

        private EntityCache(Supplier<List<E>> daoCallSupplier) {
            this.daoCallSupplier = daoCallSupplier;
        }

        private List<E> getOrCreate() {
            if (null == cachedEntities) {
                logger.debug("Delegating a call to dao layer from " + this);
                cachedEntities = daoCallSupplier.get();
                if (null == cachedEntities || cachedEntities.isEmpty()) {
                    throw new FailedGenerationException("Unable to retrieve entities from dao layer for " + this);
                }
            }
            return cachedEntities;
        }
    }

    @PostConstruct
    public void initCaches() {
        sentencePartCache = new EntityCache<>(sentencePartDao::getAll);
        conjunctionCache = new EntityCache<>(conjunctionDao::getAll);
    }

    public Wisdom generateOne() {
        logger.debug("Generating single wisdom");
        if (null == localRepository) {
            loadLocalRepository();
        }
        int rand1 = random(localRepository.size());
        int rand2 = random(localRepository.size());
        Wisdom wisdom = new Wisdom();
        wisdom.setContent(localRepository.get(rand1) + " " + localRepository.get(rand2));

        logger.debug("Wisdom has been generated. Content: " + wisdom.getContent());

        return wisdom;
    }

    public Wisdom[] generateMany(int count, GenerationStrategy generationStrategy) {
        logger.debug("Generating " + count + " wisdoms");
        Wisdom[] wisdoms = new Wisdom[count];
        for (int i = 0; i < count; i++) {
            wisdoms[i] = generationStrategy.generate();
        }
        return wisdoms;
    }

    private void loadLocalRepository() {
        logger.debug("Loading local repository");
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
        logger.debug("Generating single wisdom");
        String[] sentenceTemplates = new String[] {
                "<complex> <&complex> <complex>",
                "<complex> <complex>", //todo fix comma problem
                "<complex> <&compound> <compound>",
                "<complex> <&compound> <compound> <adverbial>",
                //todo lists
        };
        String template = sentenceTemplates[random(sentenceTemplates.length)];
        String content = parseTemplate(template);
        Wisdom wisdom = new Wisdom();
        wisdom.setTemplate(template);
        wisdom.setContent(content);

        sayAdditionalWords(wisdom);
        logger.debug("Wisdom has been generated. Content: " + wisdom.getContent());

        return wisdom;
    }

    private void sayAdditionalWords(Wisdom wisdom) {
        if (0 >= random(ADDITIONAL_WORDS_RATIO)) {
            logger.debug("Adding additional words");
            SentencePart additionalPart = pickRandomPart(RoleId.NONE);
            if (additionalPart == null) {
                throw new FailedGenerationException(
                        "Unable to retrieve sentence with role " + RoleId.NONE + " from dao layer");
            }
            logger.debug("Additional words: " + additionalPart.getContent());
            wisdom.setContent(wisdom.getContent() + ". " + additionalPart.getContent());
        }
    }

    private String parseTemplate(String template) {
        logger.debug("Parsing template " + template);
        if (0 >= template.length()) {
            throw new FailedGenerationException("Unable to parse empty template");
        }
        String[] templateParts = template.split("\\s");
        String[] filledParts = new String[templateParts.length];
        for (int i = 0; i < templateParts.length; i++) {
            if (isConjunctionVariable(templateParts[i])) {
                Conjunction conjunction = pickRandomConjunction(getRoleIdByVariable(templateParts[i]));
                if (null != conjunction) {
                    logger.debug("Conjunction content: " + conjunction.getContent());
                    filledParts[i] = conjunction.getContent();
                }
            } else if (isPartVariable(templateParts[i])) {
                SentencePart sentencePart = pickRandomPart(getRoleIdByVariable(templateParts[i]));
                if (null != sentencePart) {
                    logger.debug("Sentence part content: " + sentencePart.getContent());
                    filledParts[i] = sentencePart.getContent();
                }
            } else {
                logger.debug("Part " + templateParts[i]);
                filledParts[i] = templateParts[i];
            }
        }
        return String.join(" ", (CharSequence[]) filledParts);
    }

    private boolean isConjunctionVariable(String templatePart) {
        return Pattern.compile("<&\\w+>").matcher(templatePart).matches();
    }

    private boolean isPartVariable(String templatePart) {
        return Pattern.compile("<\\w+>").matcher(templatePart).matches();
    }

    private RoleId getRoleIdByVariable(String variable) {
        logger.debug("Getting role identifier by variable " + variable);
        Matcher matcher = Pattern.compile("<&?(\\w+)>").matcher(variable);
        if (!matcher.matches()) {
            throw new FailedGenerationException("Unable to parse template variable " + variable);
        }
        return RoleId.valueOf(matcher.replaceFirst("$1").toUpperCase());
    }

    private int random(int bound) {
        return new Random().nextInt(bound);
    }

    private SentencePart pickRandomPart(RoleId roleId) {
        logger.debug("Picking random sentence part, roleId " + roleId);
        return pickRandom(roleId, sentencePartCache);
    }

    private Conjunction pickRandomConjunction(RoleId roleId) {
        logger.debug("Picking random conjunction, roleId " + roleId);
        return pickRandom(roleId, conjunctionCache);
    }

    private <E extends RoleBased> E pickRandom(RoleId roleId, EntityCache<E> entityCache) {
        E entity;
        int i = 0;
        do {
            int randomIndex = random(entityCache.getOrCreate().size());
            entity = entityCache.getOrCreate().get(randomIndex);
            if (RANDOM_PICKING_MAX_TRIES_COUNT <= ++i) {
                entity = null;
                break;
            }
        } while (roleId.value != entity.getRole().getId());

        return entity;
    }
}
