package ru.skogmark.blogger;

/**
 * @author svip
 *         2016-12-17
 */
public class BloggerException extends RuntimeException {
    public BloggerException(String message) {
        super(message);
    }

    public BloggerException(String message, Throwable cause) {
        super(message, cause);
    }
}
