package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.gen.core.template.Template;
import ru.skogmark.go.gen.core.template.TemplateProvider;

import java.util.Random;

@Component
class TemplateSelectionHandler implements PipelineHandler<WisdomPayload> {
    private static final Random RANDOM = new Random();

    private static final Logger log = LoggerFactory.getLogger(TemplateSelectionHandler.class);

    private final TemplateProvider templateProvider;

    @Autowired
    public TemplateSelectionHandler(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        log.info("Selecting the sentence template");
        int index = random();
        Template template = templateProvider.getTemplate(index)
                .orElseThrow(() -> new PipelineHandlerException(
                        "Unable to obtain sentence template by: index=" + index));
        log.info("Template selected: template={}", template);
        wisdomPayload.setTemplate(template);
    }

    private int random() {
        return RANDOM.nextInt(templateProvider.getTemplates().size());
    }
}
