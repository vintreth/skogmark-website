package ru.skogmark.website.controller;

import org.springframework.http.HttpStatus;
import ru.skogmark.website.exception.BaseException;

/**
 * @author svip
 *         2016-05-29
 */
class HttpException extends BaseException {

    private HttpStatus status;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
