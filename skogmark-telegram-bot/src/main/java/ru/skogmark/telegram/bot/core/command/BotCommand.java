package ru.skogmark.telegram.bot.core.command;

/**
 * Central interface of any bot command
 *
 * @param <T> type of command value
 * @author svip
 *         2017-07-25
 */
public interface BotCommand<T> {
    /**
     * Executes current command
     *
     * @param value some command value
     */
    void execute(T value);
}
