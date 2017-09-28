package ru.skogmark.telegram.bot;

//import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.skogmark.telegram.bot.core.UpdateHandler;

//import javax.security.auth.message.config.AuthConfigFactory;

public abstract class AbstractBaseTelegramBotApplication implements TelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(AbstractBaseTelegramBotApplication.class);

    @Override
    public void start(Class<?> applicationClass, String[] args) {
        log.debug("Starting Telegram Bot");
        ConfigurableApplicationContext applicationContext = null;
        try {
//            if (AuthConfigFactory.getFactory() == null) {
//                AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
//            }
            applicationContext = SpringApplication.run(applicationClass, args);
            startUpdateHandler(applicationContext);
            onStartUp();
        } catch (Exception e) {
            log.error("Exception caught during application runtime", e);
            throw e;
        } finally {
            if (null != applicationContext) {
                applicationContext.close();
            }
        }
    }

    @Override
    public void onStartUp() {
        // do nothing by default
    }

    private void startUpdateHandler(ConfigurableApplicationContext applicationContext) {
        log.debug("Getting UpdateHandler bean from ApplicationContext");
        UpdateHandler updateHandler = applicationContext.getBeanFactory().getBean(UpdateHandler.class);
        updateHandler.start();
    }

    @Override
    public void stop() {
        // todo write code
    }
}