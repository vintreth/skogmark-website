package ru.skogmark.go.gen.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.config.TemplatePartConfiguration;

import java.util.Optional;

@Component
public class SentenceTemplateProvider {
    private final SentenceTemplate[] sentenceTemplates;

    @Autowired
    public SentenceTemplateProvider(TemplatePartConfiguration configuration) {
        sentenceTemplates = new SentenceTemplate[]{
                new SentenceTemplate(configuration.main(), configuration.complex(), configuration.main()),
                new SentenceTemplate(configuration.main(), configuration.comma(), configuration.main()),
                new SentenceTemplate(configuration.main(), configuration.compound(), configuration.secondary()),
                new SentenceTemplate(configuration.main(), configuration.compound(), configuration.secondary(),
                        configuration.comma(), configuration.adverbial())};
    }

    public SentenceTemplate[] getSentenceTemplates() {
        return sentenceTemplates;
    }

    public Optional<SentenceTemplate> getTemplate(int index) {
        if (index >= sentenceTemplates.length) {
            return Optional.empty();
        }
        return Optional.of(sentenceTemplates[index]);
    }
}
