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
import static ru.skogmark.go.gen.core.domain.Gender.FEMALE;
import static ru.skogmark.go.gen.core.domain.Gender.MALE;
import static ru.skogmark.go.gen.core.domain.Gender.NONE;
import static ru.skogmark.go.gen.core.domain.Gender.PLURAL;

/**
 * Handler that can random select template.
 * It stores list of templates and selects one of them randomly by weight.
 * <p>
 * todo tasks:
 * - разобраться с предлогами (уменьшить их количество?)
 * - буду там в качестве
 * - и в мыслях нет... хотя конечно иногда хочется ...
 * - три события произойдут - ...
 * - согласование по роду в main+secondary
 * - добавить Субъекту признак одушевленности и использовать как обращение
 * - predicate - это значит что ты в семье
 * - что слева ... что справа, на лице ничерта не происходит
 * <p>
 * Notes:
 * <p>
 * Шаблон в шаблоне в виде "слева %s, справа %s" противоречит всей идее текущей реализации шаблонов,
 * т.к. %s не содержит никакой информации о том, какая это часть.
 * Т.о. получается, чтобы реализовать шаблон в шаблоне в виде строки нужно писать что-то вроде
 * "слева ${subject}, справа ${subject}".
 * А т.к. от идеи парсинга строк мы отказались в пользу построения шаблонов прямо в коде, то выходит, что
 * вариант "шаблон в шаблоне" нам не подходит.
 */
@Component
class TemplateSelectionHandler implements PipelineHandler<WisdomPayload> {
    private static final long DASH = 1L;
    private static final long IS = 2L;
    private static final long AND = 3L;

    private static final long METAL_IN_RUSSIA = 290L;
    private static final long THE_SAME_AS_PLURAL = 130L;
    private static final long THE_SAME_AS_MALE = 131L;
    private static final long THE_SAME_AS_FEMALE = 728L;
    private static final long THE_SAME_AS_NONE = 729L;
    private static final long ONLY_IN_200_THOUSAND_KILOMETRES = 592L;
    private static final long STANDING_NEAR_THE_STAGE_AND_THINKING = 31L;
    private static final long HAVING_MORE_THAN_200_DOLLARS = 21L;
    private static final long COMING_OUT_ON_STAGE_LIKE = 20L;
    private static final long DEEP_THOUGHT = 10L;
    private static final long COMING_OUT_AND_SAYING = 798L;
    private static final long ON_THE_LEFT_ON_THE_RIGHT = 805L;
    private static final long IT_TURNS_OUT_THAT = 324L;
    private static final long SO_WHAT_THEY_WILL_WRITE = 138L;
    private static final long DID_THE_JOB = 134L;
    private static final long THERE_ARE_THREE_INCOMPATIBLE_THINGS = 806L;

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
        templates = ImmutableList.of(templateBuilder.single().signature().weight(0.3f).build(),
                templateBuilder.none().space().subject().weight(0.3f).build(),
                templateBuilder.subject().space().subject().build(),
                templateBuilder.subject().conjunction(IS).subject().signature().build(),
                templateBuilder.subject().conjunction(IS).subject().comma().adverbial().build(),
                templateBuilder.subject().compound().predicate().signature().weight(0.4f).build(),
                templateBuilder.adverbial().space().subject().signature().weight(0.3f).build(),

                templateBuilder.custom("слева ").subject().comma().custom("справа ").subject().weight(0.6f).build(),
                templateBuilder.list().space().predicate().conjunction(AND).predicate().weight(0.3f).build(),
                templateBuilder.sentence(METAL_IN_RUSSIA).space().subject().weight(0.55f).build(),

                templateBuilder.sentence(THE_SAME_AS_NONE).space().subject(NONE).space()
                        .sentence(ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.13f).build(),
                templateBuilder.sentence(THE_SAME_AS_MALE).space().subject(MALE).space()
                        .sentence(ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.13f).build(),
                templateBuilder.sentence(THE_SAME_AS_FEMALE).space().subject(FEMALE).space()
                        .sentence(ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.13f).build(),
                templateBuilder.sentence(THE_SAME_AS_PLURAL).space().subject(PLURAL).space()
                        .sentence(ONLY_IN_200_THOUSAND_KILOMETRES).weight(0.13f).build(),

                templateBuilder.sentence(STANDING_NEAR_THE_STAGE_AND_THINKING).space().subject().build(),
                templateBuilder.sentence(COMING_OUT_ON_STAGE_LIKE).space().predicate().weight(0.6f).build(),
                templateBuilder.predicate().conjunction(DASH).sentence(DEEP_THOUGHT).weight(0.6f).build(),
                templateBuilder.sentence(IT_TURNS_OUT_THAT).comma().predicate().signature().weight(0.4f).build(),
                templateBuilder.sentence(IT_TURNS_OUT_THAT).comma().subject().space().predicate().signature()
                        .weight(0.4f).build(),
                templateBuilder.sentence(SO_WHAT_THEY_WILL_WRITE).custom("? ").subject().custom("?").build(),
                templateBuilder.sentence(SO_WHAT_THEY_WILL_WRITE).custom("? Что ").predicate().custom("?").build(),
                templateBuilder.subject(MALE).space().sentence(DID_THE_JOB).build(),

                templateBuilder.sentence(THERE_ARE_THREE_INCOMPATIBLE_THINGS).conjunction(DASH)
                        .subject().conjunction(AND).subject().comma()
                        .subject().conjunction(AND).subject().comma()
                        .conjunction(AND).subject().conjunction(AND).subject()
                        .weight(0.6f).build());
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
