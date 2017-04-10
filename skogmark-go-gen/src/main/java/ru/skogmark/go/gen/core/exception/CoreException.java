package ru.skogmark.go.gen.core.exception;

/**
 * @author svip
 *         2016-11-27
 */
public class CoreException extends RuntimeException {
    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
