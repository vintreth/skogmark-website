package ru.skogmark.telegram.bot;

//import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

//import javax.security.auth.message.config.AuthConfigFactory;

public abstract class AbstractTelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(AbstractTelegramBotApplication.class);

    /**
     * Starts the application
     *
     * @param applicationClass main application class with main() method
     * @param args             console arguments
     */
    public void start(Class<?> applicationClass, String[] args) {
        log.debug("Starting Telegram Bot");
        ApplicationContext applicationContext = null;
        try {
//            if (AuthConfigFactory.getFactory() == null) {
//                AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
//            }
            applicationContext = SpringApplication.run(applicationClass, args);
            onStartUp(applicationContext);
        } catch (Exception e) {
            log.error("Exception caught during application runtime", e);
            if (null != applicationContext) {
                beforeStop(applicationContext);
            }
        }
    }

    /**
     * Custom actions for current application implementations
     * By default this method will be call after application context created
     *
     * @param applicationContext created ApplicationContext
     */
    public void onStartUp(ApplicationContext applicationContext) {
    }

    /**
     * Custom actions for current application implementations
     *
     * @param applicationContext created ApplicationContext
     */
    public void beforeStop(ApplicationContext applicationContext) {
    }
}