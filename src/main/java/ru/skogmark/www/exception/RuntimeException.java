package ru.skogmark.www.exception;

/**
 * @author kbogdanov 01.06.16
 */
public class RuntimeException extends java.lang.RuntimeException {

    public RuntimeException() {
        super();
    }

    public RuntimeException(String message) {
        super(message);
    }

    public RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeException(Throwable cause) {
        super(cause);
    }
}
