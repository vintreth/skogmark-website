package ru.skogmark.telegram.go.bot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.PostScheduler;
import ru.skogmark.go.blogger.config.BloggerSettings;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Handler to handle {@link BloggerApplication#onStartUp(ApplicationContext)} method
 */
@Component
class OnStartUpHandler implements Handler {
    private static final Logger log = LoggerFactory.getLogger(OnStartUpHandler.class);

    private final BloggerSettings bloggerSettings;
    private final PostScheduler postScheduler;
    private final ScheduledExecutorService executor;

    public OnStartUpHandler(BloggerSettings bloggerSettings, PostScheduler postScheduler,
                            @Qualifier("postSchedulerExecutor") ScheduledExecutorService executor) {
        this.bloggerSettings = bloggerSettings;
        this.postScheduler = postScheduler;
        this.executor = executor;
    }

    @Override
    public void handle() {
        postScheduler.checkPostWhileStarting();
        log.debug("Starting scheduled executor");
        executor.scheduleAtFixedRate(postScheduler::schedule, 0L,
                bloggerSettings.getPostSchedulerParams().getTaskIntervalSec(), TimeUnit.SECONDS);
    }
}
