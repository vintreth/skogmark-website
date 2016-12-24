package ru.skogmark.go.blogger.config;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-12-17
 */
public class ConfigurationLoadingException extends ApplicationLevelException {
    public ConfigurationLoadingException(String message) {
        super(message);
    }

    public ConfigurationLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
