package ru.skogmark.go.gen.core.pipeline;

public interface PipelineHandler<T> {
    void handle(T payload);
}
