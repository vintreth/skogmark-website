package ru.skogmark.go.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.skogmark.telegram.bot.AbstractBaseTelegramBotApplication;
import ru.skogmark.telegram.bot.TelegramBotApplication;

/**
 * Central class of the blogger application
 */
@SpringBootApplication
public class BloggerApplication extends AbstractBaseTelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(BloggerApplication.class);

    public static void main(String[] args) {
        TelegramBotApplication application = new BloggerApplication();
        application.start(BloggerApplication.class, args);
    }

    @Override
    public void onStartUp(ApplicationContext applicationContext) {
        super.onStartUp(applicationContext);
        Handler handler = applicationContext.getBean(OnStartUpHandler.class);
        handler.handle();
    }

    /**
     * Stops the application
     */
    @Override
    public void beforeStop(ApplicationContext applicationContext) {
        super.beforeStop(applicationContext);
        Handler handler = applicationContext.getBean(BeforeStopHandler.class);
        handler.handle();
    }
}
