package ru.skogmark.telegram.bot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.JacksonObjectMapperSerializerAdapter;
import ru.skogmark.common.http.Serializer;
import ru.skogmark.telegram.bot.api.TelegramBotApiUrlProvider;
import ru.skogmark.telegram.bot.core.UpdateHandler;
import ru.skogmark.telegram.bot.core.UpdateHandlerExecutorHolder;
import ru.skogmark.telegram.bot.core.client.UpdateClient;

import java.util.concurrent.Executors;

/**
 * Class for configuring beans
 *
 * @author svip
 * 2017-07-26
 */
@Configuration
public class TelegramBotConfiguration {
    private static final String TELEGRAM_BOT_TOKEN_CONFIG_FILE = "telegram-bot-token.xml";

    /**
     * Configuration factory bean
     */
    @Bean
    public ConfigurationFactory configurationFactory() {
        return new ConfigurationFactory();
    }

    /**
     * Application {@link Serializer} interface implementation
     */
    @Bean
    public Serializer serializer() {
        return new JacksonObjectMapperSerializerAdapter();
    }

    /**
     * Object with bot token value
     */
    @Bean
    public TelegramBotToken telegramBotToken(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramBotToken.class, TELEGRAM_BOT_TOKEN_CONFIG_FILE);
    }

    @Bean
    public TelegramBotApiUrlProvider telegramBotApiUrlProvider(TelegramBotToken telegramBotToken) {
        return new TelegramBotApiUrlProvider(telegramBotToken);
    }

    @Bean
    public UpdateClient updateClient(HttpRequest httpRequest, TelegramBotApiUrlProvider urlProvider) {
        return new UpdateClient(httpRequest, urlProvider);
    }

    /**
     * Executor holder for update handler
     */
    @Bean
    public UpdateHandlerExecutorHolder updateHandlerExecutorHolder() {
        return new UpdateHandlerExecutorHolder(
                Executors.newSingleThreadScheduledExecutor(
                        runnable -> new Thread(runnable, "updateHandler-thread")));
    }

    /**
     * Update handler
     */
    @Bean
    public UpdateHandler updateHandler(UpdateClient updateClient, UpdateHandlerExecutorHolder executorHolder) {
        return new UpdateHandler(updateClient, executorHolder);
    }
}
