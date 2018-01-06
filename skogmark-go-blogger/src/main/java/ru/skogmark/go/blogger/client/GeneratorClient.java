package ru.skogmark.go.blogger.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skogmark.common.http.HttpMethod;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestHeader;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.blogger.config.BloggerSettings;

/**
 * Http client for making requests to go-generator service
 */
@Service
public class GeneratorClient {
    private final BloggerSettings bloggerSettings;
    private final HttpRequest httpRequest;

    @Autowired
    public GeneratorClient(BloggerSettings bloggerSettings, HttpRequest httpRequest) {
        this.bloggerSettings = bloggerSettings;
        this.httpRequest = httpRequest;
    }

    /**
     * Gets random wisdom from the generator
     *
     * @return wisdom object
     */
    public Wisdom getRandomWisdom() {
        HttpRequestHeader header = new HttpRequestHeader();
        header.setHttpMethod(HttpMethod.GET);
        header.setUrl(bloggerSettings.getGeneratorResourceUrl());
        return httpRequest.makeRequest(header, null, Wisdom.class);
    }
}
