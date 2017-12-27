package ru.skogmark.go.gen.core.pipeline;

public interface PipelineHandler<T> {
    T handle(T payload);
}
