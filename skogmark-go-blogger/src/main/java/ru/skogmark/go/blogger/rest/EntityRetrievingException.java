package ru.skogmark.go.blogger.rest;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
public class EntityRetrievingException extends ApplicationLevelException {
    public EntityRetrievingException(String message) {
        super(message);
    }

    public EntityRetrievingException(String message, Throwable cause) {
        super(message, cause);
    }
}
