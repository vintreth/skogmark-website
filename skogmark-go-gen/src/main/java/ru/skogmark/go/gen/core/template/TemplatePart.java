package ru.skogmark.go.gen.core.template;

import java.util.Optional;

public interface TemplatePart {
    Optional<String> getContent();

    String getCode();
}
