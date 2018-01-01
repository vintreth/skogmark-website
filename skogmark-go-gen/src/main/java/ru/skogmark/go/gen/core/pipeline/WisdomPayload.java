package ru.skogmark.go.gen.core.pipeline;

import ru.skogmark.go.gen.core.domain.SentenceTemplate;

public class WisdomPayload {
    private SentenceTemplate sentenceTemplate;
    private String[] valueParts;

    public SentenceTemplate getSentenceTemplate() {
        return sentenceTemplate;
    }

    public void setSentenceTemplate(SentenceTemplate sentenceTemplate) {
        this.sentenceTemplate = sentenceTemplate;
    }

    public String[] getValueParts() {
        return valueParts;
    }

    public void setValueParts(String[] valueParts) {
        this.valueParts = valueParts;
    }
}
