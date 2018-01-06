package ru.skogmark.telegram.go.bot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.config.BloggerSettings;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
class BeforeStopHandler implements Handler {
    private static final Logger log = LoggerFactory.getLogger(BeforeStopHandler.class);

    private final BloggerSettings bloggerSettings;
    private final ScheduledExecutorService executor;

    public BeforeStopHandler(BloggerSettings bloggerSettings,
                             @Qualifier("postSchedulerExecutor") ScheduledExecutorService executor) {
        this.bloggerSettings = bloggerSettings;
        this.executor = executor;
    }

    @Override
    public void handle() {
        try {
            log.info("Stopping the application. Awaiting termination.");
            executor.awaitTermination(bloggerSettings.getAwaitTerminationTimeoutSec(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("InterruptedException occurred while awaiting scheduler termination", e);
            Thread.currentThread().interrupt();
        } finally {
            if (!executor.isTerminated()) {
                log.warn("Scheduler termination timeout");
                executor.shutdownNow();
            }
        }
    }
}
