package ru.skogmark.telegram.bot.core.update;

import java.util.concurrent.ScheduledExecutorService;

class DefaultUpdateEventConsumer implements UpdateEventConsumer {
    private final ScheduledExecutorService executor;
    private final UpdateBlockingQueueTopic topic;

    public DefaultUpdateEventConsumer(ScheduledExecutorService executor, UpdateBlockingQueueTopic topic) {
        this.executor = executor;
        this.topic = topic;
    }

    @Override
    public void notifyListeners() {

    }
}
