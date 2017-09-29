package ru.skogmark.telegram.bot.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.skogmark.telegram.bot.api.dto.Update;
import ru.skogmark.telegram.bot.core.client.UpdateClient;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Update handler retrieves a bunch of messages with update data from Telegram API
 * and handles it in some way
 *
 * @author svip
 * 2017-07-25
 */
public class UpdateHandler {
    private static final long SCHEDULER_INITIAL_DELAY = 0L;
    private static final long SCHEDULER_DELAY = 5L;
    private static final TimeUnit SCHEDULER_TIME_UNIT = TimeUnit.SECONDS;

    private static final Logger log = LoggerFactory.getLogger(UpdateHandler.class);

    private final UpdateClient updateClient;
    private final UpdateHandlerExecutorHolder executorHolder;

    private ScheduledFuture<?> updateFuture;

    public UpdateHandler(UpdateClient updateClient, UpdateHandlerExecutorHolder executorHolder) {
        this.updateClient = updateClient;
        this.executorHolder = executorHolder;
    }

    /**
     * Starts the update handler
     */
    public void start() {
        log.debug("Starting UpdateHandler");
        //todo: move delays to config
        executorHolder.getExecutor().scheduleWithFixedDelay(
                this::callGetUpdates, SCHEDULER_INITIAL_DELAY, SCHEDULER_DELAY, SCHEDULER_TIME_UNIT);
    }

    private void callGetUpdates() {
        List<Update> updates = updateClient.getUpdates();
        log.debug("Got updates " + updates);
    }

    /**
     * Stops the update handler
     */
    public void stop() {
        log.debug("Stopping UpdateHandler");
        if (null != updateFuture) {
            updateFuture.cancel(true);
        }
    }
}
