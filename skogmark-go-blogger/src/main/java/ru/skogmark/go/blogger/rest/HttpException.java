package ru.skogmark.go.blogger.rest;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
public class HttpException extends ApplicationLevelException {
    private int responseCode;
    private String responseBody;

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(int responseCode, String message, String responseBody) {
        super(message);
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
