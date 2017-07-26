package ru.skogmark.telegram.bot;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.skogmark.telegram.bot.core.UpdateHandler;

import javax.security.auth.message.config.AuthConfigFactory;

@SpringBootApplication
public class TelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(TelegramBotApplication.class);

    public static void main(String[] args) {
        log.debug("Starting Telegram Bot");
        ConfigurableApplicationContext applicationContext = null;
        try {
            if (AuthConfigFactory.getFactory() == null) {
                AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
            }
            applicationContext = SpringApplication.run(TelegramBotApplication.class, args);
            startUpdateHandler(applicationContext);
        } finally {
            if (null != applicationContext) {
                applicationContext.close();
            }
        }
    }

    private static void startUpdateHandler(ConfigurableApplicationContext applicationContext) {
        log.debug("Getting UpdateHandler bean from ApplicationContext");
        UpdateHandler updateHandler = applicationContext.getBeanFactory().getBean(UpdateHandler.class);
        updateHandler.start();
    }
}