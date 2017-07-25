package ru.skogmark.go.blogger.rest;

import org.apache.log4j.Logger;
import ru.skogmark.common.http.HttpException;
import ru.skogmark.common.http.HttpRequest;

/**
 * Local mode stub for http request
 *
 * @author svip
 *         2016-12-24
 */
public class LocalStubHttpRequest implements HttpRequest {
    private static final String LOGGER_MESSAGE = "Sending request %s %s";
    private static final Logger logger = Logger.getLogger(LocalStubHttpRequest.class);

    @Override
    public String doGet(String url) throws HttpException {
        logger.info(String.format(LOGGER_MESSAGE, Method.GET, url));
        return "{\"content\":\"stub message\"}";
    }

    @Override
    public String doPost(String url, String body) {
        logger.info(String.format(LOGGER_MESSAGE, Method.POST, url));
        return null;
    }

    @Override
    public String doPut(String url, String body) {
        logger.info(String.format(LOGGER_MESSAGE, Method.PUT, url));
        return null;
    }

    @Override
    public String doDelete(String url, String body) {
        logger.info(String.format(LOGGER_MESSAGE, Method.DELETE, url));
        return null;
    }
}
