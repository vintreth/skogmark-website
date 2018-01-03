package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum ConjunctionType {
    COMPLEX(0),
    COMPOUND(1),
    COMMA(2),
    EMPTY(3);

    public final int value;

    ConjunctionType(int value) {
        this.value = value;
    }

    public static Optional<ConjunctionType> getByValue(int value) {
        return Arrays.stream(values())
                .filter(conjunctionType -> conjunctionType.value == value)
                .findFirst();
    }
}
