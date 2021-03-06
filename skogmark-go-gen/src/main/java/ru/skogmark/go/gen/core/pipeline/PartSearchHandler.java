package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Component
class PartSearchHandler implements PipelineHandler<WisdomPayload> {
    private static final Logger log = LoggerFactory.getLogger(PartSearchHandler.class);

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        requireNonNull(wisdomPayload.getTemplate(), "Sentence template should not be null");
        log.info("Searching parts for template: template={}", wisdomPayload.getTemplate());
        List<String> contentParts = wisdomPayload.getTemplate().getTemplateParts().stream()
                .map(templatePart -> templatePart.getContent()
                        .orElseThrow(() -> new PipelineHandlerException(
                                "Unable to obtain content from dao: templatePart=" + templatePart)))
                .collect(Collectors.toList());
        log.info("Search complete: contentParts={}", contentParts);
        wisdomPayload.setContentParts(contentParts);
    }
}
