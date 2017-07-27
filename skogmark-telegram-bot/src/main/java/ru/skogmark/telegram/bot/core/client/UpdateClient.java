package ru.skogmark.telegram.bot.core.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.common.http.HttpMethod;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestHeader;
import ru.skogmark.telegram.bot.api.TelegramBotApiMethod;
import ru.skogmark.telegram.bot.api.TelegramBotApiUrlProvider;
import ru.skogmark.telegram.bot.api.request.UpdateRequest;
import ru.skogmark.telegram.bot.api.dto.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Client for making update requests
 *
 * @author svip
 *         2017-07-27
 */
@Component
public class UpdateClient {
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String CONTENT_TYPE = "application/json";

    private final HttpRequest httpRequest;
    private final TelegramBotApiUrlProvider urlProvider;

    @Autowired
    public UpdateClient(HttpRequest httpRequest, TelegramBotApiUrlProvider urlProvider) {
        this.httpRequest = httpRequest;
        this.urlProvider = urlProvider;
    }

    /**
     * Retrieves update objects from Telegram API
     *
     * @return list of update objects
     */
    public List<Update> getUpdates() {
        HttpRequestHeader header = new HttpRequestHeader();
        header.setHttpMethod(HttpMethod.POST);
        header.setUrl(urlProvider.getMethodUrl(TelegramBotApiMethod.GET_UPDATES));
        header.setDefaultCharset(DEFAULT_CHARSET);
        header.setContentType(CONTENT_TYPE);
        header.setUserAgent(USER_AGENT);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setOffset(0);
        updateRequest.setLimit(100);

        httpRequest.makeRequest(header, updateRequest);
        return new ArrayList<>();
    }
}
