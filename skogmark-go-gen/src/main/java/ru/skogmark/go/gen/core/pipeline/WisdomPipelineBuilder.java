package ru.skogmark.go.gen.core.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WisdomPipelineBuilder {
    private final TemplateSelectionHandler templateSelectionHandler;
    private final PartSearchHandler partSearchHandler;
    private final WisdomFormationHandler wisdomFormationHandler;
    private final WisdomMapperHandler wisdomMapperHandler;

    @Autowired
    public WisdomPipelineBuilder(TemplateSelectionHandler templateSelectionHandler,
                                 PartSearchHandler partSearchHandler,
                                 WisdomFormationHandler wisdomFormationHandler,
                                 WisdomMapperHandler wisdomMapperHandler) {
        this.templateSelectionHandler = templateSelectionHandler;
        this.partSearchHandler = partSearchHandler;
        this.wisdomFormationHandler = wisdomFormationHandler;
        this.wisdomMapperHandler = wisdomMapperHandler;
    }

    public Pipeline<WisdomPayload> build() {
        return new LinkedListPipeline<WisdomPayload>()
                .addLast(templateSelectionHandler)
                .addLast(partSearchHandler)
                .addLast(wisdomFormationHandler)
                .addLast(wisdomMapperHandler);
    }
}
