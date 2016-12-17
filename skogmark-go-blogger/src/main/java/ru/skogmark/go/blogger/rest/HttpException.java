package ru.skogmark.go.blogger.rest;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
public class HttpException extends ApplicationLevelException {
    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
