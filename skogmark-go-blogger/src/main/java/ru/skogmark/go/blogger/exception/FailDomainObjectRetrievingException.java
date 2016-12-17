package ru.skogmark.go.blogger.exception;

/**
 * @author svip
 *         2016-12-17
 */
public class FailDomainObjectRetrievingException extends ApplicationLevelException {
    public FailDomainObjectRetrievingException(String message) {
        super(message);
    }

    public FailDomainObjectRetrievingException(String message, Throwable cause) {
        super(message, cause);
    }
}
