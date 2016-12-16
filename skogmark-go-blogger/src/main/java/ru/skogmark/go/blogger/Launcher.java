package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;

/**
 * Bootstrapping the application
 */
public class Launcher {
    private static final Logger logger = Logger.getLogger(Launcher.class);

    /**
     * Main method to bootstrap
     * @param args console arguments
     */
    public static void main(String[] args) {
        BloggerApplication bloggerApplication = new BloggerApplication();
        try {
            bloggerApplication.start();
        } catch (Exception e) {
            logger.error("Exception caught while starting application. Application will be stopped.", e);
            bloggerApplication.stop();
        }
    }
}
