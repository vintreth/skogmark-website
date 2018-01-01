package ru.skogmark.go.gen.core.domain;

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
}
