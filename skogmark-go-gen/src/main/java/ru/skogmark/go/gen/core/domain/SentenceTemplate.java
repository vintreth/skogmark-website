package ru.skogmark.go.gen.core.domain;

import ru.skogmark.go.gen.core.dao.TemplatePartDaoProxy;

public class SentenceTemplate {
    private final TemplatePartDaoProxy[] templateParts;

    public SentenceTemplate(TemplatePartDaoProxy... templateParts) {
        this.templateParts = templateParts;
    }

    public TemplatePartDaoProxy[] getTemplateParts() {
        return templateParts;
    }
}
