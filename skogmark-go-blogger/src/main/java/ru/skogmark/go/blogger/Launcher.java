package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;

/**
 * Bootstrapping the application
 */
@Deprecated
public class Launcher {
    private static final Logger logger = Logger.getLogger(Launcher.class);

    private Launcher() {
    }

    /**
     * Main method to bootstrap
     * @param args console arguments
     */
    public static void main(String[] args) {
        logger.debug("Creating application instance");
        GoBloggerApplication application = new GoBloggerApplication();
        //todo add stop and restart commands
        try {
            application.start();
        } catch (Exception e) {
            logger.error("Exception caught while starting application. Application will be stopped.", e);
            application.stop();
        }
    }
}
