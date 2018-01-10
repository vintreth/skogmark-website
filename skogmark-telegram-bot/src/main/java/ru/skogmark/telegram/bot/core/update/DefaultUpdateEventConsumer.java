package ru.skogmark.telegram.bot.core.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.telegram.bot.api.dto.Update;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class DefaultUpdateEventConsumer implements UpdateEventConsumer {
    // todo move setting to config
    private static final long SCHEDULER_INITIAL_DELAY = 0L;
    private static final long SCHEDULER_DELAY = 100L;

    private final ScheduledExecutorService executor;
    private final UniqueBlockingQueueTopic<Update> topic;

    private static final Logger log = LoggerFactory.getLogger(DefaultUpdateEventConsumer.class);

    public DefaultUpdateEventConsumer(ScheduledExecutorService executor, UniqueBlockingQueueTopic<Update> topic) {
        this.executor = executor;
        this.topic = topic;
    }

    @Override
    public void start() {
        log.info("Starting UpdateEventConsumer");
        // todo save offset
        executor.scheduleWithFixedDelay(() -> topic.peek().ifPresent(this::notifyListeners), SCHEDULER_INITIAL_DELAY,
                SCHEDULER_DELAY, MILLISECONDS);
    }

    @Override
    public void stop() {

    }

    private void notifyListeners(Update update) {
//        log.debug("Notifying listeners about upcoming update: update={}", update);
    }
}
