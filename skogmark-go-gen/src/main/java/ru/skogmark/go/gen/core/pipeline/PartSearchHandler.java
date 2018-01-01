package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

class PartSearchHandler implements PipelineHandler<WisdomPayload> {
    private static final Logger log = LoggerFactory.getLogger(PartSearchHandler.class);

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        requireNonNull(wisdomPayload.getSentenceTemplate(), "Sentence template should not be null");
        log.info("Searching parts for template: template={}", wisdomPayload.getSentenceTemplate());
//        Arrays.stream(wisdomPayload.getSentenceTemplate().value)
//                .map(sentencePart -> );
    }
}
