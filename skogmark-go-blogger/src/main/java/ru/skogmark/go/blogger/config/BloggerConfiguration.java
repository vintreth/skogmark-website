package ru.skogmark.go.blogger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.go.blogger.blog.telegram.TelegramSettings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Blogger application beans configuration
 */
@Configuration
public class BloggerConfiguration {
    private static final String TELEGRAM_SETTINGS_PATH = "telegram-settings.xml";
    private static final String BLOGGER_SETTINGS_PATH = "blogger-settings.xml";

    /**
     * Application configuration bean
     *
     * @param configurationFactory configuration factory bean
     * @return application configuration
     */
    @Bean
    public BloggerSettings bloggerSettings(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(BloggerSettings.class, BLOGGER_SETTINGS_PATH);
    }

    @Bean
    public TelegramSettings telegramConfiguration(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramSettings.class, TELEGRAM_SETTINGS_PATH);
    }

    /**
     * Executor for {@link ru.skogmark.go.blogger.blog.PostScheduler}
     */
    @Bean
    public ScheduledExecutorService postSchedulerExecutor() {
        return Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "postScheduler"));
    }
}
