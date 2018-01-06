package ru.skogmark.telegram.bot.api;

import ru.skogmark.telegram.bot.core.config.TelegramBotToken;

import java.util.Optional;

/**
 * Class for providing completed API urls
 *
 * @author svip
 * 2017-07-28
 */
public class ApiUrlProvider {
    private static final String API_URL_PATTERN = "https://api.telegram.org/bot%s/%s";
    private static final String TOKEN_PROPERTY_NAME = "telegram.bot.token";

    private final TelegramBotToken telegramBotToken;

    public ApiUrlProvider(TelegramBotToken telegramBotToken) {
        this.telegramBotToken = telegramBotToken;
    }

    /**
     * Builds full url to API method
     *
     * @param method method
     * @return completed url
     */
    public String getMethodUrl(ApiMethod method) {
        String token = Optional.ofNullable(System.getProperty(TOKEN_PROPERTY_NAME))
                .orElse(telegramBotToken.getValue());
        return String.format(API_URL_PATTERN, token, method.name);
    }
}
