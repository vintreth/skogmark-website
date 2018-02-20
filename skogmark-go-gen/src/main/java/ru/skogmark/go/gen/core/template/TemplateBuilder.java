package ru.skogmark.go.gen.core.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.dao.SentenceDao;
import ru.skogmark.go.gen.core.domain.Gender;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.old.Conjunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMMA;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPLEX;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPOUND;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.EMPTY;
import static ru.skogmark.go.gen.core.domain.SentenceRole.ADVERBIAL;
import static ru.skogmark.go.gen.core.domain.SentenceRole.LIST;
import static ru.skogmark.go.gen.core.domain.SentenceRole.MAIN;
import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SECONDARY;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SINGLE;

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

    public TemplateBuilder custom(String content) {
        templateParts.add(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.ofNullable(content);
            }

            @Override
            public String getCode() {
                return content;
            }
        });
        return this;
    }

    public TemplateBuilder sentence(long id) {
        templateParts.add(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.ofNullable(sentenceDao.getById(id)).map(Sentence::getContent);
            }

            @Override
            public String getCode() {
                return "SENTENCE#" + id;
            }
        });
        return this;
    }

    public TemplateBuilder none() {
        templateParts.add(new SentenceTemplatePart(NONE, sentenceDao));
        return this;
    }

    public TemplateBuilder main() {
        templateParts.add(new SentenceTemplatePart(MAIN, sentenceDao));
        return this;
    }

    public TemplateBuilder main(Gender gender) {
        templateParts.add(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.ofNullable(sentenceDao.getRandomByRoleAndGender(MAIN, gender)).map(Sentence::getContent);
            }

            @Override
            public String getCode() {
                return MAIN.toString();
            }
        });
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

    public TemplateBuilder single() {
        templateParts.add(new SentenceTemplatePart(SINGLE, sentenceDao));
        return this;
    }

    public TemplateBuilder list() {
        templateParts.add(new SentenceTemplatePart(LIST, sentenceDao));
        return this;
    }

    public TemplateBuilder conjunction(long id) {
        templateParts.add(new DoubleSpaceFormationDecorator(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.ofNullable(conjunctionDao.getById(id)).map(Conjunction::getContent);
            }

            @Override
            public String getCode() {
                return "CONJUNCTION#" + id;
            }
        }));
        return this;
    }

    public TemplateBuilder complex() {
        templateParts.add(new DoubleSpaceFormationDecorator(new ConjunctionTemplatePart(
                COMPLEX, conjunctionDao)));
        return this;
    }

    public TemplateBuilder compound() {
        templateParts.add(new CompoundFormationDecorator(new ConjunctionTemplatePart(
                COMPOUND, conjunctionDao)));
        return this;
    }

    public TemplateBuilder comma() {
        templateParts.add(new RightSpaceFormationDecorator(new ConjunctionTemplatePart(COMMA, conjunctionDao)));
        return this;
    }

    public TemplateBuilder empty() {
        templateParts.add(new ConjunctionTemplatePart(EMPTY, conjunctionDao));
        return this;
    }
}
