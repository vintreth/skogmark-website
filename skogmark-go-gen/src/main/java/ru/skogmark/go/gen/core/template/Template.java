package ru.skogmark.go.gen.core.template;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public class Template {
    private final int id;
    private final List<TemplatePart> templateParts;
    private final float weight;

    public Template(int id, List<TemplatePart> templateParts, float weight) {
        this.id = id;
        this.templateParts = ImmutableList.copyOf(templateParts);
        this.weight = weight;
    }

    public int getId() {
        return id;
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
                "id=" + id + ", " +
                "templateParts=" + templateParts + ", " +
                "weight=" + weight +
                '}';
    }
}
