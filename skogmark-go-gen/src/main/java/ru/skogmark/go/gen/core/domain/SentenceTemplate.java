package ru.skogmark.go.gen.core.domain;

import ru.skogmark.go.gen.core.dao.ConjunctionDaoProxy;
import ru.skogmark.go.gen.core.dao.DaoProxy;

import java.util.Arrays;
import java.util.Optional;

import static ru.skogmark.go.gen.core.dao.SentenceDaoProxy.ADVERBIAL;
import static ru.skogmark.go.gen.core.dao.SentenceDaoProxy.MAIN;
import static ru.skogmark.go.gen.core.dao.SentenceDaoProxy.SECONDARY;

public enum SentenceTemplate {
    COMPLEX(new DaoProxy[]{MAIN, ConjunctionDaoProxy.COMPLEX, MAIN}),
    COMPLEX_COMMA(new DaoProxy[]{MAIN, ConjunctionDaoProxy.COMMA, MAIN}),
    COMPOUND(new DaoProxy[]{MAIN, ConjunctionDaoProxy.COMPOUND, SECONDARY}),
    COMPOUND_EX(new DaoProxy[]{
            MAIN, ConjunctionDaoProxy.COMPOUND, SECONDARY, ConjunctionDaoProxy.COMMA, ADVERBIAL});

    public final DaoProxy[] value;

    SentenceTemplate(DaoProxy[] value) {
        this.value = value;
    }

    public static Optional<SentenceTemplate> getByOrdinal(int ordinal) {
        return Arrays.stream(values())
                .filter(sentenceTemplate -> sentenceTemplate.ordinal() == ordinal)
                .findFirst();
    }
}
