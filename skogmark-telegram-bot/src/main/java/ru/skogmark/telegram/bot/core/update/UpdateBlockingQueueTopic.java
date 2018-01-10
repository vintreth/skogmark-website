package ru.skogmark.telegram.bot.core.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.telegram.bot.api.dto.Update;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

class UpdateBlockingQueueTopic {
    private static final int QUEUE_CAPACITY = 1000;
    private static final int QUEUE_OFFER_TIMEOUT_SEC = 10;

    private static final Logger log = LoggerFactory.getLogger(UpdateBlockingQueueTopic.class);

    private final BlockingQueue<Update> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public synchronized boolean offer(Update update) throws InterruptedException {
        if (!queue.contains(update)) {
            log.debug("Offering new update to topic: update={}", update);
            return queue.offer(update, QUEUE_OFFER_TIMEOUT_SEC, SECONDS);
        }
        return false;
    }

    public Update peek() {
        return null;
    }
}
