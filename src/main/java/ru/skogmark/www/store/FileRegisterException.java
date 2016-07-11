package ru.skogmark.www.store;

/**
 * @author svip
 *         2016-07-11
 */
public class FileRegisterException extends ru.skogmark.www.exception.RuntimeException {

    public FileRegisterException() {
        super();
    }

    public FileRegisterException(String message) {
        super(message);
    }

    public FileRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileRegisterException(Throwable cause) {
        super(cause);
    }
}
