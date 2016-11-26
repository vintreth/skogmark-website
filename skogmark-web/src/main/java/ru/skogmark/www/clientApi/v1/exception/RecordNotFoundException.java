package ru.skogmark.www.clientApi.v1.exception;

import ru.skogmark.www.exception.BaseRuntimeException;

/**
 * @author kbogdanov 01.06.16
 */
public class RecordNotFoundException extends BaseRuntimeException {

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
