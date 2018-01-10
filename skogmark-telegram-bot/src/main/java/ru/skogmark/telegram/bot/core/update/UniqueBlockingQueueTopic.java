package ru.skogmark.telegram.bot.core.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.SECONDS;

class UniqueBlockingQueueTopic<T> {
    // todo move setting to config
    private static final int QUEUE_CAPACITY = 1000;
    private static final int QUEUE_OFFER_TIMEOUT_SEC = 10;

    private static final Logger log = LoggerFactory.getLogger(UniqueBlockingQueueTopic.class);

    private final BlockingQueue<T> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private final Object lock = new Object();

    public boolean offer(@Nonnull T item) throws InterruptedException {
        requireNonNull(item, "Topic item should not be null");
        synchronized (lock) {
            if (queue.contains(item)) {
                log.debug("Item is in topic already: item={}", item);
                return false;
            }
            log.debug("Offering new item to topic: item={}", item);
            boolean offered = queue.offer(item, QUEUE_OFFER_TIMEOUT_SEC, SECONDS);
            log.debug("Notifying thread that new item is available: queue={}", queue);
            lock.notifyAll();
            return offered;
        }
    }

    /**
     * Blocking peek
     */
    public Optional<T> peek() {
        synchronized (lock) {
            T item;
            try {
                while (null == (item = queue.peek())) {
                    log.debug("Waiting for a new item from topic: queue={}", queue);
                    lock.wait();
                }
                return Optional.of(item);
            } catch (InterruptedException e) {
                log.warn("Thread was interrupted while waiting for a new item from topic: queue={}", queue);
                Thread.currentThread().interrupt();
            }
            return Optional.empty();
        }
    }
}
