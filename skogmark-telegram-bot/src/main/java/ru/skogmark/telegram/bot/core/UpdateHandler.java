package ru.skogmark.telegram.bot.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skogmark.common.http.HttpRequest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
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

    private static final Logger log = LoggerFactory.getLogger(UpdateHandler.class);

    private final HttpRequest httpRequest;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> updateFuture;

    @Autowired
    public UpdateHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * Starts the update handler
     */
    public void start() {
        log.debug("Starting UpdateHandler");
        //todo: move delays to config
        scheduledExecutorService.scheduleWithFixedDelay(
            this::callGetUpdates, SCHEDULER_INITIAL_DELAY, SCHEDULER_DELAY, SCHEDULER_TIME_UNIT);
    }

    private void callGetUpdates() {
//        httpRequest.doGet();
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
