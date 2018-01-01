package ru.skogmark.go.gen.core.domain;

public enum ConjunctionType implements SentencePart {
    COMPLEX(0),
    COMPOUND(1),
    COMMA(2);

    public final int value;

    ConjunctionType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
