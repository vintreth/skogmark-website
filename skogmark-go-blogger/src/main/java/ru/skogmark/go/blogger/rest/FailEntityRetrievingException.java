package ru.skogmark.go.blogger.rest;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
public class FailEntityRetrievingException extends ApplicationLevelException {
    public FailEntityRetrievingException(String message) {
        super(message);
    }

    public FailEntityRetrievingException(String message, Throwable cause) {
        super(message, cause);
    }
}
