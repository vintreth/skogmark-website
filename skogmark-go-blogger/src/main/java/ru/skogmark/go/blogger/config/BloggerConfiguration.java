package ru.skogmark.go.blogger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.LocalStubHttpRequest;
import ru.skogmark.common.http.Serializer;
import ru.skogmark.common.http.SerializerAwareHttpRequest;
import ru.skogmark.go.blogger.blog.telegram.TelegramConfiguration;
import ru.skogmark.telegram.bot.core.config.TelegramBotConfiguration;

/**
 * Blogger application beans configuration
 */
@Configuration
@Import({TelegramBotConfiguration.class})
public class BloggerConfiguration {
    private static final String TELEGRAM_CONFIG_PATH = "telegram-config.xml";
    private static final String APPLICATION_CONFIG_PATH = "application-config.xml";

    /**
     * Application configuration bean
     *
     * @param configurationFactory configuration factory bean
     * @return application configuration
     */
    @Bean
    public ApplicationConfiguration applicationConfiguration(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(ApplicationConfiguration.class, APPLICATION_CONFIG_PATH);
    }

    /**
     * Factory method to instantiate {@link HttpRequest} object
     */
    @Bean
    public HttpRequest httpRequest(Serializer serializer, ApplicationConfiguration applicationConfiguration) {
        if (applicationConfiguration.isLocalMode()) {
            return new LocalStubHttpRequest();
        }
        return new SerializerAwareHttpRequest(serializer);
    }

    @Bean
    public TelegramConfiguration telegramConfiguration(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramConfiguration.class, TELEGRAM_CONFIG_PATH);
    }
}
