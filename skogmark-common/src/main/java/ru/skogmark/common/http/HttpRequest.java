package ru.skogmark.common.http;

/**
 * Http request interface
 */
public interface HttpRequest {
    String makeRequest(HttpRequestHeader httpRequestHeader, Object body);

    <T> T makeRequest(HttpRequestHeader httpRequestHeader, Object body, Class<T> resultType);
}
