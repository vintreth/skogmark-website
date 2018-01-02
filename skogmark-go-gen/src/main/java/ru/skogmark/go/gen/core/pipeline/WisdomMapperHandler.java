package ru.skogmark.go.gen.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.skogmark.go.api.Wisdom;

import static java.util.Objects.requireNonNull;

@Component
class WisdomMapperHandler implements PipelineHandler<WisdomPayload> {
    private static final Logger log = LoggerFactory.getLogger(WisdomMapperHandler.class);

    @Override
    public void handle(WisdomPayload payload) {
        requireNonNull(payload.getFormattedContent(), "Content should not be null");
        log.info("Creating wisdom: content={}", payload.getFormattedContent());
        Wisdom wisdom = new Wisdom();
        wisdom.setContent(payload.getFormattedContent());
        wisdom.setTemplate(payload.getTemplate().asString());
        log.info("Wisdom created: wisdom={}", wisdom);
        payload.setWisdom(wisdom);
    }
}
