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
    private static final long CONJUNCTION_ID_IS = 1L;
    private static final long CONJUNCTION_ID_AND = 3L;

    private static final long SENTENCE_ID_METAL_IN_RUSSIA = 290L;
    private static final long SENTENCE_ID_THE_SAME_AS_PLURAL = 130L;
    private static final long SENTENCE_ID_THE_SAME_AS_MALE = 131L;
    private static final long SENTENCE_ID_THE_SAME_AS_FEMALE = 728L;
    private static final long SENTENCE_ID_THE_SAME_AS_NONE = 729L;
    private static final long SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES = 592L;
    private static final long SENTENCE_ID_STANDING_NEAR_THE_STAGE_AND_THINKING = 31L;
    private static final long SENTENCE_ID_HAVING_MORE_THAN_200_DOLLARS = 21L;
    private static final long SENTENCE_ID_BEING_ON_STAGE = 20L;
    private static final long SENTENCE_ID_DEEP_THOUGHT = 10L;

    private final List<Template> templates;

    @Autowired
    public TemplateProvider(TemplateBuilder templateBuilder) {
        templates = ImmutableList.of(templateBuilder.single().build(),
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
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_NONE).empty().main(NONE).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).build(),
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_MALE).empty().main(MALE).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).build(),
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_FEMALE).empty().main(FEMALE).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).build(),
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_PLURAL).empty().main(PLURAL).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).build(),
                templateBuilder.sentence(SENTENCE_ID_STANDING_NEAR_THE_STAGE_AND_THINKING).empty().main().build(),
                templateBuilder.sentence(SENTENCE_ID_BEING_ON_STAGE).empty().secondary().build(),
                templateBuilder.secondary().conjunction(CONJUNCTION_ID_IS).sentence(SENTENCE_ID_DEEP_THOUGHT).build());
        // todo буду там в качестве
        // todo и в мыслях нет... хотя конечно иногда хочется...
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
