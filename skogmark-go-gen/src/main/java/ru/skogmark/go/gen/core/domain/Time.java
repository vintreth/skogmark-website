package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Time {
    PAST(0),
    PRESENT(1),
    FUTURE(2);

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
