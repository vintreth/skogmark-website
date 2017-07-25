package ru.skogmark.go.blogger.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.http.GenericHttpRequest;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;

/**
 * @author svip
 *         2016-12-24
 */
@Configuration
public class HttpRequestFactory {
    @Bean(name = "httpRequest")
    public HttpRequest getHttpRequest(ApplicationConfiguration configuration) {
        if (configuration.isLocalMode()) {
            return new LocalStubHttpRequest();
        }
        GenericHttpRequest httpRequest = new GenericHttpRequest();
        httpRequest.setDefaultCharset(configuration.getDefaultCharset());
        httpRequest.setUserAgent(configuration.getUserAgent());
        return httpRequest;
    }
}
