package ru.skogmark.telegram.bot.core.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.telegram.bot.api.dto.Update;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Default implementation of {@link UpdateEventProducer}
 */
class DefaultUpdateEventProducer implements UpdateEventProducer {
    private static final long SCHEDULER_INITIAL_DELAY = 0L;
    private static final long SCHEDULER_DELAY = 5L;

    private static final Logger log = LoggerFactory.getLogger(DefaultUpdateEventProducer.class);

    private final UpdateClient updateClient;
    private final ScheduledExecutorService executor;
    private final UpdateBlockingQueueTopic topic;

    private ScheduledFuture<?> updateFuture;

    public DefaultUpdateEventProducer(UpdateClient updateClient, ScheduledExecutorService executor,
                                      UpdateBlockingQueueTopic topic) {
        this.updateClient = updateClient;
        this.executor = executor;
        this.topic = topic;
    }

    @Override
    public void start() {
        log.debug("Starting UpdateEventProducer");
        //todo: move delays to config
        updateFuture = executor.scheduleWithFixedDelay(this::handle, SCHEDULER_INITIAL_DELAY, SCHEDULER_DELAY, SECONDS);
    }

    private void handle() {
        try {
            List<Update> updates = updateClient.getUpdates();
            log.debug("Got updates " + updates);
            updates.forEach(update -> {
                try {
                    topic.offer(update);
                } catch (InterruptedException e) {
                    log.warn("Thread was interrupted while waiting to offer new update to the topic: update={}", update);
                    Thread.currentThread().interrupt();
                }
            });
        } catch (Exception e) {
            log.error("Exception caught while retrieving updates", e);
        }
    }

    @Override
    public void stop() {
        log.debug("Stopping UpdateEventProducer");
        if (null != updateFuture) {
            updateFuture.cancel(true);
        }
    }
}
