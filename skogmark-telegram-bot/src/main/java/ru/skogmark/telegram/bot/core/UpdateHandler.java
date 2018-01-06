package ru.skogmark.telegram.bot.core;

/**
 * Update handler retrieves a bunch of messages with update data from Telegram API
 * and handles it in some way
 *
 * @author svip
 * 2017-07-25
 */
public interface UpdateHandler {
    /**
     * Starts the update handler
     */
    void start();

    /**
     * Stops the update handler
     */
    void stop();
}
