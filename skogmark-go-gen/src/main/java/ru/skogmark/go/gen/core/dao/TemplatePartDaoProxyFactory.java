package ru.skogmark.go.gen.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.domain.ConjunctionType;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.SentenceRole;
import ru.skogmark.go.gen.core.domain.old.Conjunction;

import java.util.Optional;

import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMMA;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPLEX;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPOUND;
import static ru.skogmark.go.gen.core.domain.SentenceRole.ADVERBIAL;
import static ru.skogmark.go.gen.core.domain.SentenceRole.MAIN;
import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SECONDARY;

@Component
public class TemplatePartDaoProxyFactory {
    private final SentenceDao sentenceDao;
    private final ConjunctionDao conjunctionDao;

    private final TemplatePartDaoProxy none;
    private final TemplatePartDaoProxy main;
    private final TemplatePartDaoProxy secondary;
    private final TemplatePartDaoProxy adverbial;
    private final TemplatePartDaoProxy complex;
    private final TemplatePartDaoProxy compound;
    private final TemplatePartDaoProxy comma;

    @Autowired
    public TemplatePartDaoProxyFactory(SentenceDao sentenceDao, ConjunctionDao conjunctionDao) {
        this.sentenceDao = sentenceDao;
        this.conjunctionDao = conjunctionDao;

        none = new SentenceDaoProxy(NONE);
        main = new SentenceDaoProxy(MAIN);
        secondary = new SentenceDaoProxy(SECONDARY);
        adverbial = new SentenceDaoProxy(ADVERBIAL);
        complex = new ConjunctionDaoProxy(COMPLEX);
        compound = new ConjunctionDaoProxy(COMPOUND);
        comma = new ConjunctionDaoProxy(COMMA);
    }

    public TemplatePartDaoProxy none() {
        return none;
    }

    public TemplatePartDaoProxy main() {
        return main;
    }

    public TemplatePartDaoProxy secondary() {
        return secondary;
    }

    public TemplatePartDaoProxy adverbial() {
        return adverbial;
    }

    public TemplatePartDaoProxy complex() {
        return complex;
    }

    public TemplatePartDaoProxy compound() {
        return compound;
    }

    public TemplatePartDaoProxy comma() {
        return comma;
    }

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

    private class ConjunctionDaoProxy implements TemplatePartDaoProxy {
        private final ConjunctionType conjunctionType;

        ConjunctionDaoProxy(ConjunctionType conjunctionType) {
            this.conjunctionType = conjunctionType;
        }

        @Override
        public Optional<String> getRandomContent() {
            return Optional.ofNullable(conjunctionDao.getRandomConjunctionByType(conjunctionType))
                    .map(Conjunction::getContent);
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
}
