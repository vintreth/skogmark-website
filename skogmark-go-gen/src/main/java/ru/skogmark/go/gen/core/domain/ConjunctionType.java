package ru.skogmark.go.gen.core.domain;

public enum ConjunctionType {
    COMPLEX(0),
    COMPOUND(1),
    COMMA(2);

    public final int value;

    ConjunctionType(int value) {
        this.value = value;
    }
}
