package ru.skogmark.go.gen.core.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.dao.SentenceDao;

import java.util.ArrayList;
import java.util.List;

import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMMA;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPLEX;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPOUND;
import static ru.skogmark.go.gen.core.domain.SentenceRole.ADVERBIAL;
import static ru.skogmark.go.gen.core.domain.SentenceRole.MAIN;
import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SECONDARY;

@Component
public class TemplateBuilder {
    private final SentenceDao sentenceDao;
    private final ConjunctionDao conjunctionDao;

    private List<TemplatePart> templateParts = new ArrayList<>();

    @Autowired
    public TemplateBuilder(SentenceDao sentenceDao, ConjunctionDao conjunctionDao) {
        this.sentenceDao = sentenceDao;
        this.conjunctionDao = conjunctionDao;
    }

    public Template build() {
        Template template = new Template(templateParts);
        templateParts = new ArrayList<>();
        return template;
    }

    public TemplateBuilder none() {
        templateParts.add(new SentenceTemplatePart(NONE, sentenceDao));
        return this;
    }

    public TemplateBuilder main() {
        templateParts.add(new SentenceTemplatePart(MAIN, sentenceDao));
        return this;
    }

    public TemplateBuilder secondary() {
        templateParts.add(new SentenceTemplatePart(SECONDARY, sentenceDao));
        return this;
    }

    public TemplateBuilder adverbial() {
        templateParts.add(new SentenceTemplatePart(ADVERBIAL, sentenceDao));
        return this;
    }

    public TemplateBuilder complex() {
        templateParts.add(new ConjunctionTemplatePart(COMPLEX, conjunctionDao));
        return this;
    }

    public TemplateBuilder compound() {
        templateParts.add(new ConjunctionTemplatePart(COMPOUND, conjunctionDao));
        return this;
    }

    public TemplateBuilder comma() {
        templateParts.add(new ConjunctionTemplatePart(COMMA, conjunctionDao));
        return this;
    }
}
