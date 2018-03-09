package ru.skogmark.go.gen.core.pipeline;

/**
 * Pipeline pattern
 * In software engineering, a pipeline consists of a chain of processing elements (processes, threads, coroutines,
 * functions, etc.), arranged so that the output of each element is the input of the next; the name is by analogy
 * to a physical pipeline.
 * <p>
 * Usually some amount of buffering is provided between consecutive elements. The information that flows
 * in these pipelines is often a stream of records, bytes or bits, and the elements of a pipeline may be called filters;
 * this is also called the pipes and filters design pattern. Connecting elements into a pipeline is analogous
 * to function composition.
 *
 * @param <T> type of pipeline payload (transfer entity of current pipeline)
 */
public interface Pipeline<T> {
    /**
     * Adds the first handler of this pipeline
     * In case this method called twice, adds a new handler before the first one.
     *
     * @param pipelineHandler pipeline handler
     * @return this pipeline object
     */
    Pipeline<T> addFirst(PipelineHandler<T> pipelineHandler);

    Pipeline<T> addLast(PipelineHandler<T> pipelineHandler);

    void flow(T payload);
}
