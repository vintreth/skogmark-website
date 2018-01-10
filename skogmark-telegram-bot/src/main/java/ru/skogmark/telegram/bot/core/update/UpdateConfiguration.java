package ru.skogmark.telegram.bot.core.update;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.telegram.bot.api.ApiUrlProvider;
import ru.skogmark.telegram.bot.api.dto.Update;
import ru.skogmark.telegram.bot.core.config.TelegramBotSettings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class UpdateConfiguration {
    /**
     * Client for updates
     */
    @Bean
    UpdateClient updateClient(HttpRequest httpRequest, ApiUrlProvider urlProvider) {
        return new UpdateClient(httpRequest, urlProvider);
    }

    /**
     * Executor holder for {@link UpdateEventProducer}
     */
    @Bean
    ScheduledExecutorService updateEventProducerExecutor() {
        return Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "updateEventProducer"));
    }

    /**
     * Topic with updates
     */
    @Bean
    UniqueBlockingQueueTopic<Update> updateTopic() {
        return new UniqueBlockingQueueTopic<>();
    }

    /**
     * UpdateEventProducer bean
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    UpdateEventProducer updateEventProducer(UpdateClient updateClient, TelegramBotSettings settings,
                                            @Qualifier("updateEventProducerExecutor") ScheduledExecutorService executor,
                                            UniqueBlockingQueueTopic<Update> topic) {
        if (settings.isLocalMode()) {
            return new LocalStubUpdateEventProducer();
        }
        return new DefaultUpdateEventProducer(updateClient, executor, topic);
    }

    /**
     * Executor holder for {@link UpdateEventConsumer}
     */
    @Bean
    ScheduledExecutorService updateEventConsumerExecutor() {
        return Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "updateEventConsumer"));
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    UpdateEventConsumer updateEventConsumer(@Qualifier("updateEventConsumerExecutor") ScheduledExecutorService executor,
                                            UniqueBlockingQueueTopic<Update> topic) {
        return new DefaultUpdateEventConsumer(executor, topic);
    }
}
