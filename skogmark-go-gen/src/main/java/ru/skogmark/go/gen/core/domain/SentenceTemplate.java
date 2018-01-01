package ru.skogmark.go.gen.core.domain;

import java.util.Arrays;
import java.util.Optional;

import static ru.skogmark.go.gen.core.domain.SentenceRole.ADVERBIAL;
import static ru.skogmark.go.gen.core.domain.SentenceRole.MAIN;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SECONDARY;

public enum SentenceTemplate {
    COMPLEX(new SentencePart[]{MAIN, ConjunctionType.COMPLEX, MAIN}),
    COMPLEX_COMMA(new SentencePart[]{MAIN, ConjunctionType.COMMA, MAIN}),
    COMPOUND(new SentencePart[]{MAIN, ConjunctionType.COMPOUND, SECONDARY}),
    COMPOUND_EX(new SentencePart[]{MAIN, ConjunctionType.COMPOUND, SECONDARY, ConjunctionType.COMMA, ADVERBIAL});

    public final SentencePart[] value;

    SentenceTemplate(SentencePart[] value) {
        this.value = value;
    }

    public static Optional<SentenceTemplate> getByOrdinal(int ordinal) {
        return Arrays.stream(values())
                .filter(sentenceTemplate -> sentenceTemplate.ordinal() == ordinal)
                .findFirst();
    }
}
