package ru.skogmark.go.blogger.config;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
public class FailConfigurationLoadingException extends ApplicationLevelException {
    public FailConfigurationLoadingException(String message) {
        super(message);
    }

    public FailConfigurationLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
