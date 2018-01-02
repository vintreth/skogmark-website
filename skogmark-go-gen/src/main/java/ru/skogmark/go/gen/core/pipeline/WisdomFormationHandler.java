package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

@Component
class WisdomFormationHandler implements PipelineHandler<WisdomPayload> {
    private static final Logger log = LoggerFactory.getLogger(WisdomFormationHandler.class);

    @Override
    public void handle(WisdomPayload wisdomPayload) {
        requireNonNull(wisdomPayload.getContentParts(), "Content parts should not be null");
        checkArgument(!wisdomPayload.getContentParts().isEmpty(), "Content parts should not be empty");
        log.info("Formatting wisdom content: contentParts={}", wisdomPayload.getContentParts());
        wisdomPayload.setFormattedContent(String.join(" ", wisdomPayload.getContentParts()));
    }
}
