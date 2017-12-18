package ru.skogmark.go.gen.core;

import ru.skogmark.go.gen.core.exception.CoreException;

/**
 * @author svip
 *         2016-11-27
 */
public class GenerationException extends CoreException {
    public GenerationException(String message) {
        super(message);
    }

    public GenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
