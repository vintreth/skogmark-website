package ru.skogmark.go.blogger.blog;

import ru.skogmark.go.blogger.exception.ApplicationLevelException;

/**
 * @author ksbogdan
 *         20.12.2016 15:20
 */
public class PostingException extends ApplicationLevelException {
    public PostingException(String message) {
        super(message);
    }

    public PostingException(String message, Throwable cause) {
        super(message, cause);
    }
}
