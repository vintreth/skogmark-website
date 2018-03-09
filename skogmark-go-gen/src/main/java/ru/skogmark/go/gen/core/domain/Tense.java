package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Tense {
    INFINITIVE(0),
    PAST(1),
    PRESENT(2),
    FUTURE(3);

    public final int value;

    Tense(int value) {
        this.value = value;
    }

    public static Optional<Tense> getByValue(int value) {
        return Arrays.stream(values())
                .filter(tense -> tense.value == value)
                .findFirst();
    }
}
