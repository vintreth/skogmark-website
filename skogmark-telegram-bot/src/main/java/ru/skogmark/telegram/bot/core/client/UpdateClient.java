package ru.skogmark.telegram.bot.core.client;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.common.http.HttpMethod;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestHeader;

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

    @Autowired
    public UpdateClient(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * Retrieves update objects from Telegram API
     *
     * @return list of update objects
     */
    public List<Update> getUpdates() {
        HttpRequestHeader header = new HttpRequestHeader();
        header.setHttpMethod(HttpMethod.POST);
        header.setUrl("");
        header.setDefaultCharset(DEFAULT_CHARSET);
        header.setContentType(CONTENT_TYPE);
        header.setUserAgent(USER_AGENT);

        GetUpdates requestBody = new GetUpdates();
        requestBody.offset(0);
        requestBody.limit(100);

        httpRequest.makeRequest(header, requestBody);
        return new ArrayList<>();
    }
}
