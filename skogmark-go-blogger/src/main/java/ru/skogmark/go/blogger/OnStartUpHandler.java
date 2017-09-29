package ru.skogmark.go.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.PostScheduler;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Handler to handle {@link BloggerApplication#onStartUp(ApplicationContext)} method
 */
@Component
class OnStartUpHandler implements Handler {
    private static final Logger log = LoggerFactory.getLogger(OnStartUpHandler.class);

    private final ApplicationConfiguration applicationConfiguration;
    private final PostScheduler postScheduler;
    private final ScheduledExecutorService executor;

    public OnStartUpHandler(ApplicationConfiguration applicationConfiguration, PostScheduler postScheduler,
                            @Qualifier("postSchedulerExecutor") ScheduledExecutorService executor) {
        this.applicationConfiguration = applicationConfiguration;
        this.postScheduler = postScheduler;
        this.executor = executor;
    }

    @Override
    public void handle() {
        log.info(String.format("Local mode is %s", applicationConfiguration.isLocalMode() ? "enabled" : "disabled"));
        postScheduler.checkPostWhileStarting();

        log.debug("Starting scheduled executor");
        executor.scheduleAtFixedRate(() -> {
            log.debug("Running task");
            postScheduler.beABlogger();
            log.debug("Done");
        }, 0L, applicationConfiguration.getPostSchedulerParams().getTaskIntervalSec(), TimeUnit.SECONDS);
    }
}
