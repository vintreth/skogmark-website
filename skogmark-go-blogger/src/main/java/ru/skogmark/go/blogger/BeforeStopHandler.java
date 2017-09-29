package ru.skogmark.go.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
class BeforeStopHandler implements Handler {
    private static final Logger log = LoggerFactory.getLogger(BeforeStopHandler.class);

    private final ApplicationConfiguration applicationConfiguration;
    private final ScheduledExecutorService executor;

    public BeforeStopHandler(ApplicationConfiguration applicationConfiguration,
                             @Qualifier("postSchedulerExecutor") ScheduledExecutorService executor) {
        this.applicationConfiguration = applicationConfiguration;
        this.executor = executor;
    }

    @Override
    public void handle() {
        try {
            log.info("Stopping the application. Awaiting termination.");
            executor.awaitTermination(applicationConfiguration.getAwaitTerminationTimeoutSec(), TimeUnit.SECONDS);
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
