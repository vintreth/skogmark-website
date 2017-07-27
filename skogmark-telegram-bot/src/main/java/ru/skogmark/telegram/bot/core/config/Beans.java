package ru.skogmark.telegram.bot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestFactory;

/**
 * Class for configuring beans
 *
 * @author svip
 *         2017-07-26
 */
@Configuration
public class Beans {
    private static final String TELEGRAM_BOT_TOKEN_CONFIG_FILE = "telegram-bot-token.xml";

    /**
     * Factory method to instantiate {@link HttpRequest} object
     */
    @Bean
    public HttpRequest getHttpRequest() {
        return HttpRequestFactory.newGenericHttpRequest();
    }

    /**
     * Object with bot token value
     */
    @Bean
    public TelegramBotToken getTelegramBotToken() {
        return new ConfigurationFactory().loadConfiguration(TelegramBotToken.class, TELEGRAM_BOT_TOKEN_CONFIG_FILE);
    }
}
