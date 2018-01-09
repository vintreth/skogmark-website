package ru.skogmark.telegram.bot.core.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.telegram.bot.api.dto.Update;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Default implementation of {@link UpdateHandler}
 */
public class DefaultUpdateHandler implements UpdateHandler {
    private static final long SCHEDULER_INITIAL_DELAY = 0L;
    private static final long SCHEDULER_DELAY = 5L;

    private static final int QUEUE_CAPACITY = 100;
    private static final int QUEUE_OFFER_TIMEOUT_SEC = 10;

    private static final Logger log = LoggerFactory.getLogger(DefaultUpdateHandler.class);

    private final UpdateClient updateClient;
    private final ScheduledExecutorService executor;

    private final BlockingQueue<Update> topic = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    private ScheduledFuture<?> updateFuture;

    public DefaultUpdateHandler(UpdateClient updateClient, ScheduledExecutorService executor) {
        this.updateClient = updateClient;
        this.executor = executor;
    }

    @Override
    public void start() {
        log.debug("Starting UpdateHandler");
        //todo: move delays to config
        updateFuture = executor.scheduleWithFixedDelay(this::handle, SCHEDULER_INITIAL_DELAY, SCHEDULER_DELAY, SECONDS);
    }

    private void handle() {
        try {
            List<Update> updates = updateClient.getUpdates();
            log.debug("Got updates " + updates);
            updates.forEach(update -> {
                try {
                    if (!topic.contains(update)) {
                        log.debug("Offering new update to topic: update={}", update);
                        topic.offer(update, QUEUE_OFFER_TIMEOUT_SEC, SECONDS);
                    }
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
        log.debug("Stopping UpdateHandler");
        if (null != updateFuture) {
            updateFuture.cancel(true);
        }
    }
}
