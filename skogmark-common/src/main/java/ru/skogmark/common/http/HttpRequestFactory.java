package ru.skogmark.common.http;

/**
 * @author svip
 *         2017-07-27
 */
public class HttpRequestFactory {
    /**
     * Creates generic implementation of http request
     * @return http request object
     */
    public static HttpRequest newGenericHttpRequest() {
        return new GenericHttpRequest(new JsonSerializerFacade());
    }
}
