package ru.skogmark.go.gen.core.dao;

import ru.skogmark.go.gen.core.domain.ConjunctionType;

public enum ConjunctionDaoProxy implements DaoProxy {
    COMPLEX(ConjunctionType.COMPLEX),
    COMPOUND(ConjunctionType.COMPOUND),
    COMMA(ConjunctionType.COMMA);

    private final ConjunctionType conjunctionType;

    ConjunctionDaoProxy(ConjunctionType conjunctionType) {
        this.conjunctionType = conjunctionType;
    }

    @Override
    public String getRandomContent() {
        return null;
    }
}
