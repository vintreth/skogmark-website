package ru.skogmark.go.generator;

import ru.skogmark.go.exception.ApplicationLevelException;

/**
 * @author svip
 *         2016-11-27
 */
public class FailedGenerationException extends ApplicationLevelException {
    public FailedGenerationException(String message) {
        super(message);
    }

    public FailedGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
