package ru.skogmark.go.gen.core.pipeline;

import java.util.LinkedList;

public class LinkedListPipeline<T> implements Pipeline<T> {
    private final LinkedList<PipelineHandler<T>> handlers = new LinkedList<>();

    @Override
    public Pipeline<T> addFirst(PipelineHandler<T> pipelineHandler) {
        handlers.addFirst(pipelineHandler);
        return this;
    }

    @Override
    public Pipeline<T> addLast(PipelineHandler<T> pipelineHandler) {
        handlers.add(pipelineHandler);
        return this;
    }

    @Override
    public void process(T payload) {
        handlers.forEach(handler -> handler.handle(payload));
    }
}
