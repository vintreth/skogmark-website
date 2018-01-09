package ru.skogmark.telegram.bot.core.update;

import ru.skogmark.telegram.bot.core.TelegramBotException;

/**
 * @author svip
 *         2017-07-29
 */
class ApiResponseException extends TelegramBotException {
    public ApiResponseException(String message) {
        super(message);
    }

    public ApiResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
