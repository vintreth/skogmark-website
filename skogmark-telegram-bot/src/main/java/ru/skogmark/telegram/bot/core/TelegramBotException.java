package ru.skogmark.telegram.bot.core;

/**
 * @author svip
 *         2017-07-26
 */
public class TelegramBotException extends RuntimeException {
    public TelegramBotException(String message) {
        super(message);
    }

    public TelegramBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
