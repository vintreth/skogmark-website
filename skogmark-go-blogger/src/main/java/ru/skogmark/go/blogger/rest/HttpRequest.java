package ru.skogmark.go.blogger.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.config.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
@Component
public class HttpRequest {
    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String HTTP_METHOD_PUT = "PUT";
    private static final String HTTP_METHOD_DELETE = "DELETE";

    private static final Logger logger = Logger.getLogger(HttpRequest.class);

    private Configuration configuration;

    @Autowired
    public HttpRequest(Configuration configuration) {
        this.configuration = configuration;
    }

    public String doGet(String url) throws HttpException {
        return makeRequest(HTTP_METHOD_GET, url);
    }

    public String doPost(String url) throws HttpException {
        return makeRequest(HTTP_METHOD_POST, url);
    }

    public String doPut(String url) throws HttpException {
        return makeRequest(HTTP_METHOD_PUT, url);
    }

    public String doDelete(String url) throws HttpException {
        return makeRequest(HTTP_METHOD_DELETE, url);
    }

    private String makeRequest(String method, String urlAddress) throws HttpException {
        try {
            logger.debug("Sending request " + method + " " + urlAddress);
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("User-Agent", configuration.getUserAgent());
            int responseCode = connection.getResponseCode();
            if (299 < responseCode) {
                throw new HttpException(
                    "Unexpected response code " + responseCode + " for request " + method + " " + urlAddress);
            }
            return getContent(connection);
        } catch (IOException e) {
            throw new HttpException("Failure to send request " + method + " " + urlAddress, e);
        }
    }

    private String getContent(HttpURLConnection connection) throws HttpException {
        logger.debug("Retrieving content from http connection");
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream(), configuration.getDefaultCharset()))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while (null != (line = reader.readLine())) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            throw new HttpException("Exception caught while retrieving content from http connection", e);
        }
    }
}
