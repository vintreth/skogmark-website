package ru.skogmark.go.gen.core.pipeline;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.common.util.WeightedRandom;
import ru.skogmark.go.gen.core.template.Template;
import ru.skogmark.go.gen.core.template.TemplateBuilder;

import javax.annotation.Nonnull;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static ru.skogmark.go.gen.core.domain.Gender.*;

@Component
class TemplateSelectionHandler implements PipelineHandler<WisdomPayload> {
    private static final long CONJUNCTION_ID_DASH = 1L;
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
    private static final long SENTENCE_ID_COMING_OUT_AND_SAYING = 798L;

    private static final WeightedRandom RANDOM = new WeightedRandom();

    private static final Logger log = LoggerFactory.getLogger(TemplateSelectionHandler.class);

    private final TemplateBuilder templateBuilder;

    private List<Template> templates;

    @Autowired
    public TemplateSelectionHandler(@Nonnull TemplateBuilder templateBuilder) {
        this.templateBuilder = requireNonNull(templateBuilder, "templateBuilder");
        initTemplates();
    }

    private void initTemplates() {
        /*
         * todo
         * переосмыслить роли в предложении,
         * перестать клеить сложные предложения,
         * сделать разделение на Объект+Действие (Подлежащее+Сказуемое) вместо main+secondary
         * и составлять простые предложения
         */
        // todo разобраться с предлогами (уменьшить их количество?)
        templates = ImmutableList.of(templateBuilder.single().weight(0.3f).build(),
                templateBuilder.none().empty().main().build(),
                templateBuilder.main().empty().main().build(),
                templateBuilder.main().complex().main().build(),
                templateBuilder.main().complex().main().empty().adverbial().build(),
                templateBuilder.main().compound().secondary().build(),
                templateBuilder.adverbial().empty().main().build(),

                templateBuilder.custom("слева ").main().comma().custom("справа ").main().weight(0.7f).build(),
                templateBuilder.list().empty().secondary().conjunction(CONJUNCTION_ID_AND).secondary().build(),
                templateBuilder.sentence(SENTENCE_ID_METAL_IN_RUSSIA).empty().main().weight(0.6f).build(),

                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_NONE).empty().main(NONE).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.2f).build(),
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_MALE).empty().main(MALE).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.2f).build(),
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_FEMALE).empty().main(FEMALE).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.2f).build(),
                templateBuilder.sentence(SENTENCE_ID_THE_SAME_AS_PLURAL).empty().main(PLURAL).empty()
                        .sentence(SENTENCE_ID_ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.2f).build(),

                templateBuilder.sentence(SENTENCE_ID_STANDING_NEAR_THE_STAGE_AND_THINKING).empty().main().build(),
                templateBuilder.sentence(SENTENCE_ID_COMING_OUT_AND_SAYING).empty().main().build(),
                templateBuilder.sentence(SENTENCE_ID_BEING_ON_STAGE).empty().secondary().weight(0.6f).build(),
                templateBuilder.secondary().conjunction(CONJUNCTION_ID_DASH).sentence(SENTENCE_ID_DEEP_THOUGHT)
                        .weight(0.6f).build());
        // todo буду там в качестве
        // todo и в мыслях нет... хотя конечно иногда хочется ...
        // todo ... решил дела
        // todo три события произойдут - ...
        // todo есть три несовместимые вещи-...
        // todo всем крыс, хэйт, с почтением Ваш Граф
        // todo согласование по роду в main+secondary
        // todo добавить Объекту (сейчас main) признак одушевленности и использовать как обращение
        // todo и что они там напишут? ...?
    }

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        log.info("Selecting the sentence template");
        Template template = templates.get(RANDOM.pick(templates.stream()
                .mapToDouble(Template::getWeight)
                .toArray()));
        log.info("Template selected: template={}", template);
        wisdomPayload.setTemplate(template);
    }
}
