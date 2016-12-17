package ru.skogmark.go.blogger.exception;

/**
 * @author svip
 *         2016-12-17
 */
public class FailPostRetrievingException extends ApplicationLevelException {
    public FailPostRetrievingException(String message) {
        super(message);
    }

    public FailPostRetrievingException(String message, Throwable cause) {
        super(message, cause);
    }
}
