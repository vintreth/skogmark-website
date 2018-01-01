package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.domain.SentenceTemplate;
import ru.skogmark.go.gen.core.domain.SentenceTemplateProvider;

import java.util.Random;

@Component
class TemplateSelectionHandler implements PipelineHandler<WisdomPayload> {
    private static final Random RANDOM = new Random();

    private static final Logger log = LoggerFactory.getLogger(TemplateSelectionHandler.class);

    private final SentenceTemplateProvider sentenceTemplateProvider;

    @Autowired
    public TemplateSelectionHandler(SentenceTemplateProvider sentenceTemplateProvider) {
        this.sentenceTemplateProvider = sentenceTemplateProvider;
    }

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        log.info("Selecting the sentence template");
        int index = random();
        SentenceTemplate sentenceTemplate = sentenceTemplateProvider.getTemplate(index)
                .orElseThrow(() -> new PipelineHandlerException(
                        "Unable to obtain sentence template by: index=" + index));
        log.info("Template selected: sentenceTemplate={}", sentenceTemplate);
        wisdomPayload.setSentenceTemplate(sentenceTemplate);
    }

    private int random() {
        return RANDOM.nextInt(sentenceTemplateProvider.getSentenceTemplates().length);
    }
}
