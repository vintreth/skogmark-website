package ru.skogmark.go.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.skogmark.go.blogger.blog.PostScheduler;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;
import ru.skogmark.telegram.bot.AbstractBaseTelegramBotApplication;
import ru.skogmark.telegram.bot.TelegramBotApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Central class of the blogger application
 */
// todo remove componentScan and replace it with explicit bean definitions
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ru.skogmark.telegram.bot"})
public class GoBloggerApplication extends AbstractBaseTelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(GoBloggerApplication.class);

    //todo move executor to application context
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

    @Autowired
    private PostScheduler postScheduler;

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    public static void main(String[] args) {
        TelegramBotApplication application = new GoBloggerApplication();
        application.start(GoBloggerApplication.class, args);
    }

    @Override
    public void onStartUp() {
        log.debug("{} onStartUp handler", GoBloggerApplication.class);

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
    public void stop() {
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
