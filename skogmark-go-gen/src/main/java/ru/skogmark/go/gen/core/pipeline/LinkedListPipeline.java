package ru.skogmark.go.gen.core.pipeline;

import javax.annotation.Nonnull;
import java.util.LinkedList;

import static java.util.Objects.requireNonNull;

class LinkedListPipeline<T> implements Pipeline<T> {
    private final LinkedList<PipelineHandler<T>> handlers = new LinkedList<>();

    @Override
    public Pipeline<T> addFirst(@Nonnull PipelineHandler<T> pipelineHandler) {
        requireNonNull(pipelineHandler, "PipelineHandler should not be null");
        handlers.addFirst(pipelineHandler);
        return this;
    }

    @Override
    public Pipeline<T> addLast(@Nonnull PipelineHandler<T> pipelineHandler) {
        requireNonNull(pipelineHandler, "PipelineHandler should not be null");
        handlers.add(pipelineHandler);
        return this;
    }

    @Override
    public void flow(@Nonnull T payload) {
        requireNonNull(payload, "Payload should not be null");
        handlers.forEach(handler -> handler.handle(payload));
    }
}
