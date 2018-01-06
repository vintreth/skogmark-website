package ru.skogmark.go.blogger.blog;

import ru.skogmark.go.blogger.exception.BloggerException;

/**
 * @author ksbogdan
 *         20.12.2016 15:20
 */
public class PostingException extends BloggerException {
    public PostingException(String message) {
        super(message);
    }

    public PostingException(String message, Throwable cause) {
        super(message, cause);
    }
}
