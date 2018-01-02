package ru.skogmark.go.gen.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.dao.TemplatePartDaoProxyFactory;

import java.util.Optional;

@Component
public class SentenceTemplateProvider {
    private final SentenceTemplate[] sentenceTemplates;

    @Autowired
    public SentenceTemplateProvider(TemplatePartDaoProxyFactory factory) {
        sentenceTemplates = new SentenceTemplate[]{
                new SentenceTemplate(factory.none(), factory.comma(), factory.none()),
                new SentenceTemplate(factory.none(), factory.complex(), factory.none()),
                new SentenceTemplate(factory.none(), factory.complex(), factory.none(), factory.comma(),
                        factory.adverbial()),
                new SentenceTemplate(factory.main(), factory.comma(), factory.main()),
                new SentenceTemplate(factory.main(), factory.complex(), factory.main()),
                new SentenceTemplate(factory.main(), factory.complex(), factory.main(), factory.comma(),
                        factory.adverbial()),
                new SentenceTemplate(factory.main(), factory.compound(), factory.secondary()),
                new SentenceTemplate(factory.main(), factory.compound(), factory.secondary(),
                        factory.comma(), factory.adverbial())};
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
