package ru.skogmark.go.gen.core.domain;

public enum ConjunctionType {
    COMPLEX(0),
    COMPOUND(1);

    public final int code;

    ConjunctionType(int code) {
        this.code = code;
    }
}
