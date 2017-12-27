package ru.skogmark.go.gen.core;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.gen.core.pipeline.WisdomGenerationPayload;
import ru.skogmark.go.gen.core.pipeline.Pipeline;
import ru.skogmark.go.gen.core.pipeline.WisdomGenerationPipelineFactory;

/**
 * New flexible implementation of {@link WisdomGenerator}
 */
public class FlexibleWisdomGenerator implements WisdomGenerator {
    private final WisdomGenerationPipelineFactory pipelineFactory;

    @Autowired
    public FlexibleWisdomGenerator(WisdomGenerationPipelineFactory pipelineFactory) {
        this.pipelineFactory = pipelineFactory;
    }

    @Override
    public Wisdom generateOne() {
        Pipeline<WisdomGenerationPayload> pipeline = pipelineFactory.getPipeline();
        WisdomGenerationPayload payload = new WisdomGenerationPayload();
        pipeline.process(payload);
        return null;
    }

    @Override
    public Wisdom[] generateMany(int count) {
        return new Wisdom[0];
    }
}
