package ru.skogmark.telegram.bot.core;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Update handler retrieves a bunch of messages with update data from Telegram API
 * and handles it in some way
 *
 * @author svip
 *         2017-07-25
 */
@Component
@Scope("singleton")
public class UpdateHandler {
    private static final long SCHEDULER_INITIAL_DELAY = 0L;
    private static final long SCHEDULER_DELAY = 5L;
    private static final TimeUnit SCHEDULER_TIME_UNIT = TimeUnit.SECONDS;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * Starts the update handler
     */
    public void start() {
        scheduledExecutorService.scheduleWithFixedDelay(
            this::callGetUpdates, SCHEDULER_INITIAL_DELAY, SCHEDULER_DELAY, SCHEDULER_TIME_UNIT);
    }

    private void callGetUpdates() {
    }

    /**
     * Stops the update handler
     */
    public void stop() {}
}
