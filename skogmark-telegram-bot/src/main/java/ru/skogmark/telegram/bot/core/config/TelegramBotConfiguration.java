package ru.skogmark.telegram.bot.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.JacksonObjectMapperSerializerAdapter;
import ru.skogmark.common.http.LocalStubHttpRequest;
import ru.skogmark.common.http.Serializer;
import ru.skogmark.common.http.SerializerAwareHttpRequest;
import ru.skogmark.telegram.bot.api.ApiUrlProvider;
import ru.skogmark.telegram.bot.core.update.UpdateConfiguration;

/**
 * Class for configuring beans
 *
 * @author svip
 * 2017-07-26
 */
@Configuration
@Import(UpdateConfiguration.class)
public class TelegramBotConfiguration {
    private static final String TELEGRAM_BOT_TOKEN_FILE = "telegram-bot-token.xml";
    private static final String TELEGRAM_BOT_SETTINGS_FILE = "telegram-bot-settings.xml";

    private static final Logger log = LoggerFactory.getLogger(TelegramBotConfiguration.class);

    /**
     * Configuration factory bean
     */
    @Bean
    ConfigurationFactory configurationFactory() {
        return new ConfigurationFactory();
    }

    /**
     * Application {@link Serializer} interface implementation
     */
    @Bean
    Serializer serializer() {
        return new JacksonObjectMapperSerializerAdapter();
    }

    @Bean
    TelegramBotSettings telegramBotSettings(ConfigurationFactory configurationFactory) {
        TelegramBotSettings settings = configurationFactory.loadConfiguration(TelegramBotSettings.class,
                TELEGRAM_BOT_SETTINGS_FILE);
        log.info(String.format("Local mode is %s", settings.isLocalMode() ? "enabled" : "disabled"));
        return settings;
    }

    /**
     * Factory method to instantiate {@link HttpRequest} object
     */
    @Bean
    HttpRequest httpRequest(Serializer serializer, TelegramBotSettings settings) {
        if (settings.isLocalMode()) {
            return new LocalStubHttpRequest();
        }
        return new SerializerAwareHttpRequest(serializer);
    }

    /**
     * Object with bot token value
     */
    @Bean
    TelegramBotToken telegramBotToken(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramBotToken.class, TELEGRAM_BOT_TOKEN_FILE);
    }

    @Bean
    ApiUrlProvider telegramBotApiUrlProvider(TelegramBotToken telegramBotToken) {
        return new ApiUrlProvider(telegramBotToken);
    }
}
