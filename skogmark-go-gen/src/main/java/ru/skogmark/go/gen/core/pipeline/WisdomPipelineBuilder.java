package ru.skogmark.go.gen.core.pipeline;

import org.springframework.beans.factory.annotation.Autowired;

public class WisdomPipelineBuilder {
    private final TemplateSelectionHandler templateSelectionHandler;
    private final PartSearchHandler partSearchHandler;
    private final WisdomFormationHandler wisdomFormationHandler;

    @Autowired
    public WisdomPipelineBuilder(TemplateSelectionHandler templateSelectionHandler,
                                 PartSearchHandler partSearchHandler,
                                 WisdomFormationHandler wisdomFormationHandler) {
        this.templateSelectionHandler = templateSelectionHandler;
        this.partSearchHandler = partSearchHandler;
        this.wisdomFormationHandler = wisdomFormationHandler;
    }

    public Pipeline<WisdomPayload> build() {
        return new LinkedListPipeline<WisdomPayload>()
                .addLast(templateSelectionHandler)
                .addLast(partSearchHandler)
                .addLast(wisdomFormationHandler);
    }
}
