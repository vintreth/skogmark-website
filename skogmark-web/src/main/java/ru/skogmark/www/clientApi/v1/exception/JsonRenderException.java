package ru.skogmark.www.clientApi.v1.exception;

import ru.skogmark.www.exception.BaseException;

/**
 * @author svip
 *         2016-06-09
 */
public class JsonRenderException extends BaseException {

    public JsonRenderException() {
        super();
    }

    public JsonRenderException(String message) {
        super(message);
    }

    public JsonRenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonRenderException(Throwable cause) {
        super(cause);
    }
}
