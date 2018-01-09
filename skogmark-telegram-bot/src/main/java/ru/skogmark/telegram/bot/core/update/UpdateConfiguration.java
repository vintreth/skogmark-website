package ru.skogmark.telegram.bot.core.update;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.telegram.bot.api.ApiUrlProvider;
import ru.skogmark.telegram.bot.core.config.TelegramBotSettings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class UpdateConfiguration {
    @Bean
    UpdateClient updateClient(HttpRequest httpRequest, ApiUrlProvider urlProvider) {
        return new UpdateClient(httpRequest, urlProvider);
    }

    /**
     * Executor holder for update handler
     */
    @Bean
    ScheduledExecutorService updateHandlerScheduledExecutor() {
        return Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "updateHandler"));
    }

    /**
     * Topic with updates
     */
    @Bean
    UpdateBlockingQueueTopic updateBlockingQueueTopic() {
        return new UpdateBlockingQueueTopic();
    }

    /**
     * Update handler
     */
    @Bean
    UpdateEventProducer updateHandler(UpdateClient updateClient, TelegramBotSettings settings,
                                      @Qualifier("updateHandlerScheduledExecutor") ScheduledExecutorService executor,
                                      UpdateBlockingQueueTopic updateBlockingQueueTopic) {
        if (settings.isLocalMode()) {
            return new LocalStubUpdateEventProducer();
        }
        return new DefaultUpdateEventProducer(updateClient, executor, updateBlockingQueueTopic);
    }
}
