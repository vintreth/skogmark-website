package ru.skogmark.go.exception;

/**
 * @author svip
 *         2016-11-27
 */
public class ApplicationLevelException extends RuntimeException {
    public ApplicationLevelException(String message) {
        super(message);
    }

    public ApplicationLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
