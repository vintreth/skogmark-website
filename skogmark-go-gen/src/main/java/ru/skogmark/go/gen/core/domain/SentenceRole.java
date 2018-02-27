package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

public enum SentenceRole {
    /**
     * Без роли / неизвестная роль
     */
    NONE(0),

    /**
     * Подлежащее
     */
    SUBJECT(1),

    /**
     * Сказуемое
     */
    PREDICATE(2),

    /**
     * Дополнение
     */
    ADVERBIAL(3),

    /**
     * Реальная цитата, используемая самостоятельно
     */
    SINGLE(4),

    /**
     * Список с перечислениями
     */
    LIST(5),

    /**
     * Подпись
     */
    SIGNATURE(6);

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
