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
import java.util.concurrent.atomic.AtomicInteger;

import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPLEX;
import static ru.skogmark.go.gen.core.domain.ConjunctionType.COMPOUND;
import static ru.skogmark.go.gen.core.domain.SentenceRole.ADVERBIAL;
import static ru.skogmark.go.gen.core.domain.SentenceRole.LIST;
import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.PREDICATE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SIGNATURE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SINGLE;
import static ru.skogmark.go.gen.core.domain.SentenceRole.SUBJECT;

@Component
public class TemplateBuilder {
    private static final String SPACE = " ";
    private static final String COMMA = ",";

    private static final float DEFAULT_WEIGHT_VALUE = 0.5f;

    private final SentenceDao sentenceDao;
    private final ConjunctionDao conjunctionDao;

    private AtomicInteger id = new AtomicInteger();
    private List<TemplatePart> templateParts = new ArrayList<>();
    private float weight = DEFAULT_WEIGHT_VALUE;

    @Autowired
    public TemplateBuilder(SentenceDao sentenceDao, ConjunctionDao conjunctionDao) {
        this.sentenceDao = sentenceDao;
        this.conjunctionDao = conjunctionDao;
    }

    public Template build() {
        Template template = new Template(id.incrementAndGet(), templateParts, weight);
        templateParts = new ArrayList<>();
        weight = DEFAULT_WEIGHT_VALUE;
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
                return "SENTENCE-" + id;
            }
        });
        return this;
    }

    public TemplateBuilder none() {
        templateParts.add(new SentenceTemplatePart(NONE, sentenceDao));
        return this;
    }

    public TemplateBuilder subject() {
        templateParts.add(new SentenceTemplatePart(SUBJECT, sentenceDao));
        return this;
    }

    public TemplateBuilder subject(Gender gender) {
        templateParts.add(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.ofNullable(sentenceDao.getRandomByRoleAndGender(SUBJECT, gender)).map(Sentence::getContent);
            }

            @Override
            public String getCode() {
                return SUBJECT.toString();
            }
        });
        return this;
    }

    public TemplateBuilder predicate() {
        templateParts.add(new SentenceTemplatePart(PREDICATE, sentenceDao));
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

    public TemplateBuilder signature() {
        templateParts.add(new SentenceDelimiterFormationDecorator(new SentenceTemplatePart(SIGNATURE, sentenceDao)));
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
                return "CONJUNCTION-" + id;
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
        templateParts.add(new RightSpaceFormationDecorator(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.of(COMMA);
            }

            @Override
            public String getCode() {
                return COMMA;
            }
        }));
        return this;
    }

    public TemplateBuilder space() {
        templateParts.add(new TemplatePart() {
            @Override
            public Optional<String> getContent() {
                return Optional.of(SPACE);
            }

            @Override
            public String getCode() {
                return SPACE;
            }
        });
        return this;
    }

    public TemplateBuilder weight(float weight) {
        this.weight = weight;
        return this;
    }
}
