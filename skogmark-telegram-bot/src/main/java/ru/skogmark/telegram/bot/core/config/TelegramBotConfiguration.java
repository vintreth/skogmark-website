package ru.skogmark.telegram.bot.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.config.ConfigurationFactory;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.JacksonObjectMapperSerializerAdapter;
import ru.skogmark.common.http.LocalStubHttpRequest;
import ru.skogmark.common.http.Serializer;
import ru.skogmark.common.http.SerializerAwareHttpRequest;
import ru.skogmark.telegram.bot.api.ApiUrlProvider;
import ru.skogmark.telegram.bot.core.update.DefaultUpdateHandler;
import ru.skogmark.telegram.bot.core.update.LocalStubUpdateHandler;
import ru.skogmark.telegram.bot.core.update.UpdateClient;
import ru.skogmark.telegram.bot.core.update.UpdateHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Class for configuring beans
 *
 * @author svip
 * 2017-07-26
 */
@Configuration
public class TelegramBotConfiguration {
    private static final String TELEGRAM_BOT_TOKEN_FILE = "telegram-bot-token.xml";
    private static final String TELEGRAM_BOT_SETTINGS_FILE = "telegram-bot-settings.xml";

    private static final Logger log = LoggerFactory.getLogger(TelegramBotConfiguration.class);

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

    @Bean
    public TelegramBotSettings telegramBotSettings(ConfigurationFactory configurationFactory) {
        TelegramBotSettings settings =  configurationFactory.loadConfiguration(TelegramBotSettings.class,
                TELEGRAM_BOT_SETTINGS_FILE);
        log.info(String.format("Local mode is %s", settings.isLocalMode() ? "enabled" : "disabled"));
        return settings;
    }

    /**
     * Factory method to instantiate {@link HttpRequest} object
     */
    @Bean
    public HttpRequest httpRequest(Serializer serializer, TelegramBotSettings settings) {
        if (settings.isLocalMode()) {
            return new LocalStubHttpRequest();
        }
        return new SerializerAwareHttpRequest(serializer);
    }

    /**
     * Object with bot token value
     */
    @Bean
    public TelegramBotToken telegramBotToken(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramBotToken.class, TELEGRAM_BOT_TOKEN_FILE);
    }

    @Bean
    public ApiUrlProvider telegramBotApiUrlProvider(TelegramBotToken telegramBotToken) {
        return new ApiUrlProvider(telegramBotToken);
    }

    @Bean
    public UpdateClient updateClient(HttpRequest httpRequest, ApiUrlProvider urlProvider) {
        return new UpdateClient(httpRequest, urlProvider);
    }

    /**
     * Executor holder for update handler
     */
    @Bean
    public ScheduledExecutorService updateHandlerScheduledExecutor() {
        return Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "updateHandler"));
    }

    /**
     * Update handler
     */
    @Bean
    public UpdateHandler updateHandler(UpdateClient updateClient, TelegramBotSettings settings,
                                       @Qualifier("updateHandlerScheduledExecutor") ScheduledExecutorService executor) {
        if (settings.isLocalMode()) {
            return new LocalStubUpdateHandler();
        }
        return new DefaultUpdateHandler(updateClient, executor);
    }
}
