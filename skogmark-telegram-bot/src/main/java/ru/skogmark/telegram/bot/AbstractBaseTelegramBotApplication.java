package ru.skogmark.telegram.bot;

//import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import ru.skogmark.telegram.bot.core.UpdateHandler;

//import javax.security.auth.message.config.AuthConfigFactory;

public abstract class AbstractBaseTelegramBotApplication implements TelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(AbstractBaseTelegramBotApplication.class);

    @Override
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

    @Override
    public void onStartUp(ApplicationContext applicationContext) {
        log.debug("OnStartUp handler");
        startUpdateHandler(applicationContext);
    }

    private void startUpdateHandler(ApplicationContext applicationContext) {
        log.debug("Getting UpdateHandler bean from ApplicationContext");
        UpdateHandler updateHandler = applicationContext.getBean(UpdateHandler.class);
        updateHandler.start();
    }

    @Override
    public void beforeStop(ApplicationContext applicationContext) {
        UpdateHandler updateHandler = applicationContext.getBean(UpdateHandler.class);
        updateHandler.stop();
    }
}