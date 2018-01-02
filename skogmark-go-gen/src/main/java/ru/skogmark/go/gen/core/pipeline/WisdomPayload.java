package ru.skogmark.go.gen.core.pipeline;

import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.gen.core.template.Template;

import java.util.List;

public class WisdomPayload {
    private Template template;
    private List<String> contentParts;
    private String formattedContent;
    private Wisdom wisdom;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<String> getContentParts() {
        return contentParts;
    }

    public void setContentParts(List<String> contentParts) {
        this.contentParts = contentParts;
    }

    public String getFormattedContent() {
        return formattedContent;
    }

    public void setFormattedContent(String formattedContent) {
        this.formattedContent = formattedContent;
    }

    public Wisdom getWisdom() {
        return wisdom;
    }

    public void setWisdom(Wisdom wisdom) {
        this.wisdom = wisdom;
    }
}
