package ru.skogmark.go.blogger.rest;

import org.springframework.stereotype.Component;

/**
 * Http request interface
 */
@Component
public interface HttpRequest {
    enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    String doGet(String url) throws HttpException;

    String doPost(String url, String body) throws HttpException;

    String doPut(String url, String body) throws HttpException;

    String doDelete(String url, String body) throws HttpException;
}
