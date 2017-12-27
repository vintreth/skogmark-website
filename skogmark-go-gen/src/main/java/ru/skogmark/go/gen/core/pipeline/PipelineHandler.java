package ru.skogmark.go.gen.core.pipeline;

interface PipelineHandler<T> {
    void handle(T payload);
}
