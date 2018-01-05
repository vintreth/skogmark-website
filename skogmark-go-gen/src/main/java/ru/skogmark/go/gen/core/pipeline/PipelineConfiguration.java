package ru.skogmark.go.gen.core.pipeline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfiguration {
    @Bean
    public Pipeline<WisdomPayload> wisdomPipeline(TemplateSelectionHandler templateSelectionHandler,
                                                  PartSearchHandler partSearchHandler,
                                                  WisdomFormationHandler wisdomFormationHandler,
                                                  WisdomMapperHandler wisdomMapperHandler) {
        return new LinkedListPipeline<WisdomPayload>()
                .addLast(templateSelectionHandler)
                .addLast(partSearchHandler)
                .addLast(wisdomFormationHandler)
                .addLast(wisdomMapperHandler);
    }
}
