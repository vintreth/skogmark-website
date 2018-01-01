package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.go.gen.core.dao.DaoProxy;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class PartSearchHandler implements PipelineHandler<WisdomPayload> {
    private static final Logger log = LoggerFactory.getLogger(PartSearchHandler.class);

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        requireNonNull(wisdomPayload.getSentenceTemplate(), "Sentence template should not be null");
        log.info("Searching parts for template: template={}", wisdomPayload.getSentenceTemplate());
        wisdomPayload.setContentParts(Arrays.stream(wisdomPayload.getSentenceTemplate().value)
                .map(DaoProxy::getRandomContent)
                .collect(Collectors.toList()));
    }
}
