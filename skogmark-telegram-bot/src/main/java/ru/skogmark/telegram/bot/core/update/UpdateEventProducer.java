package ru.skogmark.telegram.bot.core.update;

/**
 * Update handler retrieves a bunch of messages with update data from Telegram API
 * and put it to {@link UniqueBlockingQueueTopic}
 *
 * @author svip
 * 2017-07-25
 */
public interface UpdateEventProducer {
    /**
     * Starts the update producer
     */
    void start();

    /**
     * Stops the update producer
     */
    void stop();
}
