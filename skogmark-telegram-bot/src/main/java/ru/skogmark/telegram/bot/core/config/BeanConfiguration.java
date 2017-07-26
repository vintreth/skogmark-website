package ru.skogmark.telegram.bot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.http.HttpRequest;
import ru.skogmark.common.http.HttpRequestFactory;

/**
 * Class for configuring beans
 *
 * @author svip
 *         2017-07-26
 */
@Configuration
public class BeanConfiguration {
    /**
     * Factory method to instantiate {@link HttpRequest} object
     */
    @Bean
    public HttpRequest getHttpRequest() {
        return HttpRequestFactory.newGenericHttpRequest();
    }
}
