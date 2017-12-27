package ru.skogmark.go.gen.core.domain;

public enum SentenceTemplate {
    COMPLEX("<main> & <main>"),
    COMPLEX_COMMA("<main>, <main>"),
    COMPOUND("<main> & <secondary>"),
    COMPOUND_EX("<main> & <secondary> <adverbial>");

    final String value;

    SentenceTemplate(String value) {
        this.value = value;
    }
}
