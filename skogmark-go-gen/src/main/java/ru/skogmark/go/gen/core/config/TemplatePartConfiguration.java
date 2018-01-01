package ru.skogmark.go.gen.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.dao.SentenceDao;
import ru.skogmark.go.gen.core.dao.TemplatePartDaoProxy;
import ru.skogmark.go.gen.core.domain.ConjunctionType;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.SentenceRole;

import java.util.Optional;

import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMMA;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPLEX;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPOUND;
import static ru.skogmark.go.gen.core.domain.SentenceRole.ADVERBIAL;
import static ru.skogmark.go.gen.core.domain.SentenceRole.MAIN;
import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SECONDARY;

@Configuration
public class TemplatePartConfiguration {
    @Autowired
    private SentenceDao sentenceDao;

    @Autowired
    private ConjunctionDao conjunctionDao;

    private class SentenceDaoProxy implements TemplatePartDaoProxy {
        private final SentenceRole sentenceRole;

        SentenceDaoProxy(SentenceRole sentenceRole) {
            this.sentenceRole = sentenceRole;
        }

        @Override
        public Optional<String> getRandomContent() {
            return Optional.ofNullable(sentenceDao.getRandomSentenceByRole(sentenceRole)).map(Sentence::getContent);
        }

        @Override
        public int getValue() {
            return sentenceRole.value;
        }

        @Override
        public String toString() {
            return "SentenceDaoProxy{" +
                    "sentenceRole=" + sentenceRole +
                    '}';
        }
    }

    @Bean
    public TemplatePartDaoProxy none() {
        return new SentenceDaoProxy(NONE);
    }

    @Bean
    public TemplatePartDaoProxy main() {
        return new SentenceDaoProxy(MAIN);
    }

    @Bean
    public TemplatePartDaoProxy secondary() {
        return new SentenceDaoProxy(SECONDARY);
    }

    @Bean
    public TemplatePartDaoProxy adverbial() {
        return new SentenceDaoProxy(ADVERBIAL);
    }

    private class ConjunctionDaoProxy implements TemplatePartDaoProxy {
        private final ConjunctionType conjunctionType;

        ConjunctionDaoProxy(ConjunctionType conjunctionType) {
            this.conjunctionType = conjunctionType;
        }

        @Override
        public Optional<String> getRandomContent() {
            return Optional.empty();
        }

        @Override
        public int getValue() {
            return conjunctionType.value;
        }

        @Override
        public String toString() {
            return "ConjunctionDaoProxy{" +
                    "conjunctionType=" + conjunctionType +
                    '}';
        }
    }

    @Bean
    public TemplatePartDaoProxy complex() {
        return new ConjunctionDaoProxy(COMPLEX);
    }

    @Bean
    public TemplatePartDaoProxy compound() {
        return new ConjunctionDaoProxy(COMPOUND);
    }

    @Bean
    public TemplatePartDaoProxy comma() {
        return new ConjunctionDaoProxy(COMMA);
    }
}
