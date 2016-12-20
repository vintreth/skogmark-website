package ru.skogmark.go.blogger.blog.telegram;

import ru.skogmark.go.blogger.blog.PostingException;

/**
 * @author ksbogdan
 *         20.12.2016 15:14
 */
class TelegramPostingException extends PostingException {
    public TelegramPostingException(String message) {
        super(message);
    }

    public TelegramPostingException(String message, Throwable cause) {
        super(message, cause);
    }
}
