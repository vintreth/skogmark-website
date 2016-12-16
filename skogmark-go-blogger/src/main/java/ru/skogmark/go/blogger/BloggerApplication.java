package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Central class of the blogger application
 */
class BloggerApplication {
    private static final long JOB_INTERVAL_SEC = 300L;
    private static final long AWAIT_TERMINATION_TIMEOUT_SEC = 10L;

    private static final Logger logger = Logger.getLogger(BloggerApplication.class);

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

    @Autowired
    private PostScheduler postScheduler;

    /**
     * Starts the application
     */
    public void start() {
        logger.debug("Starting application");
        logger.debug("Autowiring beans to the current application instance");
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        logger.debug("Starting scheduled executor");
        executor.scheduleAtFixedRate(postScheduler, 0L, JOB_INTERVAL_SEC, TimeUnit.SECONDS);
    }

    /**
     * Stops the application
     */
    public void stop() {
        try {
            logger.debug("Stopping the application. Awaiting termination.");
            executor.awaitTermination(AWAIT_TERMINATION_TIMEOUT_SEC, TimeUnit.SECONDS);
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
