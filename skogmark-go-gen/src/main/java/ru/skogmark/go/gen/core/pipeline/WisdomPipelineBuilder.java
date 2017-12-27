package ru.skogmark.go.gen.core.pipeline;

import org.springframework.beans.factory.annotation.Autowired;

public class WisdomPipelineBuilder {
    private final TemplateSelectionHandler templateSelectionHandler;
    private final SentenceSearchHandler sentenceSearchHandler;
    private final ConjunctionSearchHandler conjunctionSearchHandler;
    private final WisdomFormationHandler wisdomFormationHandler;

    @Autowired
    public WisdomPipelineBuilder(TemplateSelectionHandler templateSelectionHandler,
                                 SentenceSearchHandler sentenceSearchHandler,
                                 ConjunctionSearchHandler conjunctionSearchHandler,
                                 WisdomFormationHandler wisdomFormationHandler) {
        this.templateSelectionHandler = templateSelectionHandler;
        this.sentenceSearchHandler = sentenceSearchHandler;
        this.conjunctionSearchHandler = conjunctionSearchHandler;
        this.wisdomFormationHandler = wisdomFormationHandler;
    }

    public Pipeline<WisdomPayload> build() {
        return new LinkedListPipeline<WisdomPayload>()
                .addLast(templateSelectionHandler)
                .addLast(sentenceSearchHandler)
                .addLast(conjunctionSearchHandler)
                .addLast(wisdomFormationHandler);
    }
}
