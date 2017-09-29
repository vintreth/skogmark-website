package ru.skogmark.telegram.bot.core;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Holder for UpdateHandler executor
 */
public class UpdateHandlerExecutorHolder {
    private final ScheduledExecutorService executor;

    public UpdateHandlerExecutorHolder(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    /**
     * Gets the executor
     *
     * @return executor
     */
    public ScheduledExecutorService getExecutor() {
        return executor;
    }
}
