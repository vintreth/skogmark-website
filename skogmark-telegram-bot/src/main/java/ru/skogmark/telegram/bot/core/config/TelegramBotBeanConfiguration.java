package ru.skogmark.telegram.bot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.JacksonObjectMapperSerializerAdapter;
import ru.skogmark.common.http.Serializer;
import ru.skogmark.common.http.SerializerAwareHttpRequest;

/**
 * Class for configuring beans
 *
 * @author svip
 *         2017-07-26
 */
@Configuration
public class TelegramBotBeanConfiguration {
    private static final String TELEGRAM_BOT_TOKEN_CONFIG_FILE = "telegram-bot-token.xml";

    /**
     * Configuration factory bean
     */
    @Bean
    public ConfigurationFactory getConfigurationFactory() {
        return new ConfigurationFactory();
    }

    /**
     * Application {@link Serializer} interface implementation
     */
    @Bean
    public Serializer getSerializer() {
        return new JacksonObjectMapperSerializerAdapter();
    }

    /**
     * Object with bot token value
     */
    @Bean
    public TelegramBotToken getTelegramBotToken(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramBotToken.class, TELEGRAM_BOT_TOKEN_CONFIG_FILE);
    }
}
