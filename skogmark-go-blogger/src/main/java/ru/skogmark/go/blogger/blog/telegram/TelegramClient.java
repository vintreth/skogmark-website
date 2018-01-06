package ru.skogmark.go.blogger.blog.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skogmark.common.http.HttpMethod;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestHeader;
import ru.skogmark.telegram.bot.api.ApiMethod;
import ru.skogmark.telegram.bot.api.ApiUrlProvider;

/**
 * Http client to make requests to Telegram API
 */
@Service
public class TelegramClient {
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String CONTENT_TYPE = "application/json";

    private final HttpRequest httpRequest;
    private final ApiUrlProvider urlProvider;

    @Autowired
    public TelegramClient(HttpRequest httpRequest, ApiUrlProvider urlProvider) {
        this.httpRequest = httpRequest;
        this.urlProvider = urlProvider;
    }

    /**
     * Posts a message to telegram
     */
    public void postMessage(TelegramMessage message) {
        // todo handle request
        httpRequest.makeRequest(createRequestHeader(), message);
    }

    private HttpRequestHeader createRequestHeader() {
        HttpRequestHeader header = new HttpRequestHeader();
        header.setHttpMethod(HttpMethod.POST);
        header.setUrl(urlProvider.getMethodUrl(ApiMethod.SEND_MESSAGE));
        header.setDefaultCharset(DEFAULT_CHARSET);
        header.setContentType(CONTENT_TYPE);
        header.setUserAgent(USER_AGENT);
        return header;
    }
}
