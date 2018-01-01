package ru.skogmark.go.gen.core.dao;

import ru.skogmark.go.gen.core.domain.TemplatePart;

import java.util.Optional;

public interface TemplatePartDaoProxy extends TemplatePart {
    Optional<String> getRandomContent();
}
