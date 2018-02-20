package ru.skogmark.go.gen.core.template;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public class Template {
    private final List<TemplatePart> templateParts;
    private final float weight;

    public Template(List<TemplatePart> templateParts, float weight) {
        this.templateParts = ImmutableList.copyOf(templateParts);
        this.weight = weight;
    }

    public List<TemplatePart> getTemplateParts() {
        return templateParts;
    }

    public float getWeight() {
        return weight;
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
