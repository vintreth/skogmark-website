package ru.skogmark.go.gen.core.pipeline;

import ru.skogmark.go.gen.core.domain.SentenceTemplate;

import java.util.List;

public class WisdomPayload {
    private SentenceTemplate sentenceTemplate;
    private List<String> contentParts;

    public SentenceTemplate getSentenceTemplate() {
        return sentenceTemplate;
    }

    public void setSentenceTemplate(SentenceTemplate sentenceTemplate) {
        this.sentenceTemplate = sentenceTemplate;
    }

    public List<String> getContentParts() {
        return contentParts;
    }

    public void setContentParts(List<String> contentParts) {
        this.contentParts = contentParts;
    }
}
