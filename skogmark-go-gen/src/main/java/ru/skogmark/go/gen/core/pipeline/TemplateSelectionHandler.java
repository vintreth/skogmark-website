package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.go.gen.core.domain.SentenceTemplate;

import java.util.Random;

class TemplateSelectionHandler implements PipelineHandler<WisdomPayload> {
    private static final Random RANDOM = new Random();

    private static final Logger log = LoggerFactory.getLogger(TemplateSelectionHandler.class);

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        log.info("Selecting the sentence template");
        int ordinal = random();
        SentenceTemplate sentenceTemplate = SentenceTemplate.getByOrdinal(ordinal)
                .orElseThrow(() -> new TemplateSelectionException(
                        "Unable to obtain sentence template by: ordinal=" + ordinal));
        log.info("Template selected: sentenceTemplate={}", sentenceTemplate);
        wisdomPayload.setSentenceTemplate(sentenceTemplate);
    }

    private static int random() {
        return RANDOM.nextInt(SentenceTemplate.values().length);
    }
}
