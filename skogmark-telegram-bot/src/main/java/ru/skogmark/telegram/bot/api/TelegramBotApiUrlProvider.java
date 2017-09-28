package ru.skogmark.telegram.bot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.telegram.bot.core.config.TelegramBotToken;

/**
 * Class for providing completed API urls
 *
 * @author svip
 *         2017-07-28
 */
public class TelegramBotApiUrlProvider {
    private static final String API_URL_PATTERN = "https://api.telegram.org/bot%s/%s";

    private final TelegramBotToken telegramBotToken;

    @Autowired
    public TelegramBotApiUrlProvider(TelegramBotToken telegramBotToken) {
        this.telegramBotToken = telegramBotToken;
    }

    /**
     * Builds full url to API method
     *
     * @param method method
     * @return completed url
     */
    public String getMethodUrl(TelegramBotApiMethod method) {
        return String.format(API_URL_PATTERN, telegramBotToken.getValue(), method.name);
    }
}
