package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    NONE(0),
    MALE(1),
    FEMALE(2),
    PLURAL(3);

    public final int value;

    Gender(int value) {
        this.value = value;
    }

    public static Optional<Gender> getByValue(int value) {
        return Arrays.stream(values())
                .filter(gender -> gender.value == value)
                .findFirst();
    }
}
