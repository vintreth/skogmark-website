package ru.skogmark.go.blogger.client;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skogmark.common.http.HttpMethod;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestHeader;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;

/**
 * Http client for making requests to go-generator service
 */
public class GeneratorClient {
    private final ApplicationConfiguration applicationConfiguration;
    private final HttpRequest httpRequest;

    @Autowired
    public GeneratorClient(ApplicationConfiguration applicationConfiguration, HttpRequest httpRequest) {
        this.applicationConfiguration = applicationConfiguration;
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
        header.setUrl(applicationConfiguration.getGeneratorResourceUrl());
        return httpRequest.makeRequest(header, null, Wisdom.class);
    }
}
