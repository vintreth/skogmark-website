package ru.skogmark.go.gen.core.template;

import java.util.Optional;

class CommaFormationDecorator implements TemplatePart {
    private final TemplatePart templatePart;

    public CommaFormationDecorator(TemplatePart templatePart) {
        this.templatePart = templatePart;
    }

    @Override
    public Optional<String> getContent() {
        return templatePart.getContent().map(content -> content + ' ');
    }

    @Override
    public String getCode() {
        return templatePart.getCode();
    }
}
