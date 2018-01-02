package ru.skogmark.go.gen.core.domain;

import ru.skogmark.go.gen.core.dao.TemplatePartDaoProxy;

import java.util.Arrays;

public class SentenceTemplate {
    private final TemplatePartDaoProxy[] templateParts;

    public SentenceTemplate(TemplatePartDaoProxy... templateParts) {
        this.templateParts = templateParts;
    }

    public TemplatePartDaoProxy[] getTemplateParts() {
        return templateParts;
    }

    @Override
    public String toString() {
        return "SentenceTemplate{" +
                "templateParts=" + Arrays.toString(templateParts) +
                '}';
    }
}
