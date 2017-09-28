package ru.skogmark.go.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import ru.skogmark.telegram.bot.core.config.TelegramBotBeanConfiguration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Central class of the blogger application
 */
// todo remove componentScan and replace it with explicit bean definitions
@SpringBootApplication
@Import({TelegramBotBeanConfiguration.class})
public class BloggerApplication extends AbstractBaseTelegramBotApplication {
    private static final String TELEGRAM_CONFIG = "telegram-config.xml";

    private static final Logger log = LoggerFactory.getLogger(BloggerApplication.class);

    //todo move executor to application context
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private PostScheduler postScheduler;

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    public static void main(String[] args) {
        TelegramBotApplication application = new BloggerApplication();
        application.start(BloggerApplication.class, args);
    }

    @Override
    public void onStartUp() {
        log.debug("{} onStartUp handler", BloggerApplication.class);

        log.info(String.format("Local mode is %s", applicationConfiguration.isLocalMode() ? "enabled" : "disabled"));
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
    public void beforeStop() {
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

    /**
     * Factory method to instantiate {@link HttpRequest} object
     */
    @Bean
    public HttpRequest getHttpRequest(Serializer serializer) {
        if (applicationConfiguration.isLocalMode()) {
            return new LocalStubHttpRequest();
        }
        return new SerializerAwareHttpRequest(serializer);
    }

    @Bean(name = "telegramConfiguration")
    public TelegramConfiguration getTelegramConfiguration(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramConfiguration.class, TELEGRAM_CONFIG);
    }
}
