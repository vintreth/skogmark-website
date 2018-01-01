package ru.skogmark.go.gen.core.dao;

import ru.skogmark.go.gen.core.domain.SentenceRole;

public enum  SentenceDaoProxy implements DaoProxy {
    NONE(SentenceRole.NONE),
    MAIN(SentenceRole.MAIN),
    SECONDARY(SentenceRole.SECONDARY),
    ADVERBIAL(SentenceRole.ADVERBIAL);

    private final SentenceRole sentenceRole;

    SentenceDaoProxy(SentenceRole sentenceRole) {
        this.sentenceRole = sentenceRole;
    }

    @Override
    public String getRandomContent() {
        return null;
    }
}
