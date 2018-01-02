package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum SentenceRole {
    NONE(0),
    MAIN(1),
    SECONDARY(2),
    /**
     * Дополнение
     */
    ADVERBIAL(3);

    public final int value;

    SentenceRole(int value) {
        this.value = value;
    }

    public static Optional<SentenceRole> getByValue(int value) {
        return Arrays.stream(values())
                .filter(sentenceRole -> sentenceRole.value == value)
                .findFirst();
    }
}
