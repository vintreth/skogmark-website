package ru.skogmark.go.gen.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.gen.core.pipeline.Pipeline;
import ru.skogmark.go.gen.core.pipeline.WisdomPayload;
import ru.skogmark.go.gen.core.pipeline.WisdomPipelineBuilder;

/**
 * New flexible implementation of {@link WisdomGenerator}
 */
@Component
public class FlexibleWisdomGenerator implements WisdomGenerator {
    private final WisdomPipelineBuilder pipelineBuilder;

    @Autowired
    public FlexibleWisdomGenerator(WisdomPipelineBuilder pipelineBuilder) {
        this.pipelineBuilder = pipelineBuilder;
    }

    @Override
    public Wisdom generateOne() {
        Pipeline<WisdomPayload> pipeline = pipelineBuilder.build();
        WisdomPayload payload = new WisdomPayload();
        pipeline.flow(payload);
        return null;
    }

    @Override
    public Wisdom[] generateMany(int count) {
        return new Wisdom[0];
    }
}
