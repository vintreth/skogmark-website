package ru.skogmark.go.gen.core.template;

import java.util.List;
import java.util.stream.Collectors;

public class Template {
    private final List<TemplatePart> templateParts;

    public Template(List<TemplatePart> templateParts) {
        this.templateParts = templateParts;
    }

    public List<TemplatePart> getTemplateParts() {
        return templateParts;
    }

    public String asString() {
        return templateParts.stream()
                .map(TemplatePart::getCode)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateParts=" + templateParts +
                '}';
    }
}
