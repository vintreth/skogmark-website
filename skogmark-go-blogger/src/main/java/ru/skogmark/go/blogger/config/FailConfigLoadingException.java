package ru.skogmark.go.blogger.config;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
class FailConfigLoadingException extends ApplicationLevelException {
    public FailConfigLoadingException(String message) {
        super(message);
    }

    public FailConfigLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
