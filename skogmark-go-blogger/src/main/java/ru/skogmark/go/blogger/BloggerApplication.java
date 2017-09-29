package ru.skogmark.go.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.LocalStubHttpRequest;
import ru.skogmark.common.http.Serializer;
import ru.skogmark.common.http.SerializerAwareHttpRequest;
import ru.skogmark.go.blogger.blog.PostScheduler;
import ru.skogmark.go.blogger.blog.telegram.TelegramConfiguration;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;
import ru.skogmark.telegram.bot.AbstractBaseTelegramBotApplication;
import ru.skogmark.telegram.bot.TelegramBotApplication;
import ru.skogmark.telegram.bot.core.config.TelegramBotConfiguration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Central class of the blogger application
 */
// todo remove componentScan and replace it with explicit bean definitions
@SpringBootApplication
public class BloggerApplication extends AbstractBaseTelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(BloggerApplication.class);

    //todo move executor to application context
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        TelegramBotApplication application = new BloggerApplication();
        application.start(BloggerApplication.class, args);
    }

    @Override
    public void onStartUp(ApplicationContext applicationContext) {
        log.debug("OnStartUp handler", BloggerApplication.class);
        ApplicationConfiguration applicationConfiguration = applicationContext.getBean(ApplicationConfiguration.class);
        log.info(String.format("Local mode is %s", applicationConfiguration.isLocalMode() ? "enabled" : "disabled"));

        PostScheduler postScheduler = applicationContext.getBean(PostScheduler.class);
        postScheduler.checkPostWhileStarting();

        log.debug("Starting scheduled executor");
        executor.scheduleAtFixedRate(() -> {
            log.debug("Running task");
            postScheduler.beABlogger();
            log.debug("Done");
        }, 0L, applicationConfiguration.getPostSchedulerParams().getTaskIntervalSec(), TimeUnit.SECONDS);
    }

    /**
     * Stops the application
     */
    @Override
    public void beforeStop(ApplicationContext applicationContext) {
        try {
            log.info("Stopping the application. Awaiting termination.");
            ApplicationConfiguration applicationConfiguration = applicationContext.getBean(
                    ApplicationConfiguration.class);
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
