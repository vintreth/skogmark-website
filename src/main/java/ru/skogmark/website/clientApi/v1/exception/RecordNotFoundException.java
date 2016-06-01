package ru.skogmark.website.clientApi.v1.exception;

import ru.skogmark.website.exception.RuntimeException;

/**
 * @author kbogdanov 01.06.16
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
