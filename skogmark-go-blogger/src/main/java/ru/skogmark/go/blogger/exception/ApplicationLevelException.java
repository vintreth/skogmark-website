package ru.skogmark.go.blogger.exception;

/**
 * @author svip
 *         2016-12-17
 */
public class ApplicationLevelException extends Exception {
    public ApplicationLevelException(String message) {
        super(message);
    }

    public ApplicationLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
