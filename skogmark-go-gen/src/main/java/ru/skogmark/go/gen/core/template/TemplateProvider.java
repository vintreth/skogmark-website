package ru.skogmark.go.gen.core.template;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static ru.skogmark.go.gen.core.domain.Gender.FEMALE;
import static ru.skogmark.go.gen.core.domain.Gender.MALE;
import static ru.skogmark.go.gen.core.domain.Gender.NONE;
import static ru.skogmark.go.gen.core.domain.Gender.PLURAL;

@Component
public class TemplateProvider {
    private static final long CONJUNCTION_ID_AND = 3L;
    private static final long SENTENCE_ID_METAL_IN_RUSSIA = 290L;

    private final List<Template> templates;

    @Autowired
    public TemplateProvider(TemplateBuilder templateBuilder) {
        templates = ImmutableList.of(//templateBuilder.single().build(),
                templateBuilder.none().empty().main().build(),
                templateBuilder.none().empty().main().empty().adverbial().build(),
                templateBuilder.none().empty().main().compound().secondary().build(),
                templateBuilder.main().empty().main().build(),
                templateBuilder.main().complex().main().build(),
                templateBuilder.main().complex().main().comma().none().build(),
                templateBuilder.main().complex().main().empty().adverbial().build(),
                templateBuilder.main().compound().secondary().build(),
                templateBuilder.main().compound().secondary().comma().none().build(),
                templateBuilder.main().compound().secondary().empty().adverbial().build(),
                templateBuilder.adverbial().empty().main().build(),
                templateBuilder.adverbial().empty().main().comma().none().build(),
                templateBuilder.adverbial().empty().main().compound().secondary().build(),
                templateBuilder.custom("слева ").main().comma().custom("справа ").main().build(),
                templateBuilder.list().empty().secondary().conjunction(CONJUNCTION_ID_AND).secondary().build(),
                templateBuilder.sentence(SENTENCE_ID_METAL_IN_RUSSIA).empty().main().build(),
                templateBuilder.custom("такое же по строению ").main(NONE).custom(" только за 200 тысяч (или чего там?) километров").build(),
                templateBuilder.custom("такой же по строению ").main(MALE).custom(" только за 200 тысяч (или чего там?) километров").build(),
                templateBuilder.custom("такая же по строению ").main(FEMALE).custom(" только за 200 тысяч (или чего там?) километров").build(),
                templateBuilder.custom("такие же по строению ").main(PLURAL).custom(" только за 200 тысяч (или чего там?) километров").build()
        );
        // todo буду там в качестве
        // todo я у сцены стою и думаю
        // todo 200 долларов
        // todo выходят на сцену
        // todo глубокая мысль
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public Optional<Template> getTemplate(int index) {
        if (index >= templates.size()) {
            return Optional.empty();
        }
        return Optional.of(templates.get(index));
    }
}
