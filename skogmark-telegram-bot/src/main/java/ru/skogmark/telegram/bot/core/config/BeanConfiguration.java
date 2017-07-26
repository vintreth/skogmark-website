package ru.skogmark.telegram.bot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.http.GenericHttpRequest;
import ru.skogmark.common.http.HttpRequest;

/**
 * Class for configuring beans
 *
 * @author svip
 *         2017-07-26
 */
@Configuration
public class BeanConfiguration {
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Factory method to instantiate {@link ru.skogmark.common.http.HttpRequest} object
     */
    @Bean
    public HttpRequest getHttpRequest() {
        GenericHttpRequest httpRequest = new GenericHttpRequest();
        httpRequest.setDefaultCharset(DEFAULT_CHARSET);
        httpRequest.setUserAgent(USER_AGENT);
        return httpRequest;
    }
}
