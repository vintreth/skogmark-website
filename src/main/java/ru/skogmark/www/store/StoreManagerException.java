package ru.skogmark.www.store;

/**
 * @author svip
 *         2016-07-15
 */
public class StoreManagerException extends ru.skogmark.www.exception.RuntimeException {

    public StoreManagerException() {
        super();
    }

    public StoreManagerException(String message) {
        super(message);
    }

    public StoreManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreManagerException(Throwable cause) {
        super(cause);
    }

}
