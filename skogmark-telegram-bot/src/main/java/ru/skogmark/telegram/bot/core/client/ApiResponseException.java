package ru.skogmark.telegram.bot.core.client;

import ru.skogmark.telegram.bot.core.TelegramBotException;

/**
 * @author svip
 *         2017-07-29
 */
public class ApiResponseException extends TelegramBotException {
    public ApiResponseException(String message) {
        super(message);
    }

    public ApiResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
