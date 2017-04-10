package ru.skogmark.go.gen.core;

import ru.skogmark.go.gen.core.exception.CoreException;

/**
 * @author svip
 *         2016-11-27
 */
public class FailedGenerationException extends CoreException {
    public FailedGenerationException(String message) {
        super(message);
    }

    public FailedGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
