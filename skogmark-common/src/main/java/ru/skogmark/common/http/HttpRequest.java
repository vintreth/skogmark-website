package ru.skogmark.common.http;

/**
 * Http request interface
 */
public interface HttpRequest {
    enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    String doGet(String url);

    String doPost(String url, String body);

    String doPut(String url, String body);

    String doDelete(String url, String body);
}
