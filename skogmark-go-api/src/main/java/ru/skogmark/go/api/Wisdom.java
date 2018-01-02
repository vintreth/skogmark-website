package ru.skogmark.go.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author svip
 *         2016-11-26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wisdom {
    private String content;
    private String template;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "Wisdom{" +
                "content='" + content + '\'' +
                ", template='" + template + '\'' +
                '}';
    }
}
