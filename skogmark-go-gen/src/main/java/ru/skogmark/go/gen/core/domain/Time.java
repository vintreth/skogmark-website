package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Time {
    INFINITIVE(0),
    PAST(1),
    PRESENT(2),
    FUTURE(3);

    public final int value;

    Time(int value) {
        this.value = value;
    }

    public static Optional<Time> getByValue(int value) {
        return Arrays.stream(values())
                .filter(time -> time.value == value)
                .findFirst();
    }
}
