package ru.skogmark.blogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.skogmark.blogger.post.PostScheduler;
import ru.skogmark.telegram.bot.AbstractTelegramBotApplication;
import ru.skogmark.telegram.bot.core.config.TelegramBotConfiguration;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Central class of the blogger application
 */
@SpringBootApplication
@ComponentScan(basePackages = {"ru.skogmark.telegram.go.bot", "ru.skogmark.go.blogger"})
@Import({TelegramBotConfiguration.class})
public class BloggerApplication extends AbstractTelegramBotApplication {
    private static final Logger log = LoggerFactory.getLogger(BloggerApplication.class);

    public static void main(String[] args) {
        new BloggerApplication().start(BloggerApplication.class, args);
    }

    @Override
    public void onStartUp(ApplicationContext applicationContext) {
        PostScheduler postScheduler = applicationContext.getBean(PostScheduler.class);
        if (isNull(postScheduler)) {
            log.error("Unable to start PostScheduler. Application will be stopped");
            // todo stop
        } else {
            postScheduler.start();
        }
    }

    @Override
    public void beforeStop(ApplicationContext applicationContext) {
        PostScheduler postScheduler = applicationContext.getBean(PostScheduler.class);
        if (nonNull(postScheduler)) {
            postScheduler.stop();
        }
    }
}
