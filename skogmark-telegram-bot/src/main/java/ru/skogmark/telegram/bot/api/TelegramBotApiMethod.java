package ru.skogmark.telegram.bot.api;

/**
 * @author svip
 *         2017-07-28
 */
public enum TelegramBotApiMethod {
    GET_UPDATES("getUpdates"),
    SEND_MESSAGE("sendMessage");

    /**
     * Name of the method in API as string
     */
    public final String name;

    TelegramBotApiMethod(String name) {
        this.name = name;
    }
}
