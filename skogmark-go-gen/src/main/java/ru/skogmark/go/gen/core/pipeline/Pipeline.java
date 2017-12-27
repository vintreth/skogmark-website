package ru.skogmark.go.gen.core.pipeline;

public interface Pipeline<T> {
    Pipeline<T> addFirst(PipelineHandler<T> pipelineHandler);

    Pipeline<T> addLast(PipelineHandler<T> pipelineHandler);

    void process(T payload);
}
