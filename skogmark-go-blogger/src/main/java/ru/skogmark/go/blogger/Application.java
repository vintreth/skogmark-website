package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.skogmark.go.blogger.blog.Blogger;
import ru.skogmark.go.blogger.config.Configuration;
import ru.skogmark.go.blogger.exception.ApplicationLevelException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Central class of the blogger application
 */
class Application {
    private static final Logger logger = Logger.getLogger(Application.class);

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

    @Autowired
    private Blogger blogger;

    @Autowired
    private Configuration configuration;

    /**
     * Starts the application
     */
    public void start() throws ApplicationLevelException {
        logger.debug("Starting application");
        logger.debug("Autowiring beans to the current application instance");
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);

        logger.debug("Starting scheduled executor");
        executor.scheduleAtFixedRate(() -> {
            logger.debug("Running task");
            blogger.beABlogger();
            logger.debug("Done");
        }, 0L, configuration.getBloggerParams().getTaskIntervalSec(), TimeUnit.SECONDS);
    }

    /**
     * Stops the application
     */
    public void stop() {
        try {
            logger.debug("Stopping the application. Awaiting termination.");
            executor.awaitTermination(configuration.getAwaitTerminationTimeoutSec(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("InterruptedException occurred while awaiting scheduler termination", e);
            Thread.currentThread().interrupt();
        } finally {
            if (!executor.isTerminated()) {
                logger.warn("Scheduler termination timeout");
                executor.shutdownNow();
            }
        }
    }
}
