package ru.skogmark.go.blogger.rest;

import org.apache.log4j.Logger;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Generic implementation of http request
 *
 * @author svip
 *         2016-12-24
 */
public class GenericHttpRequest implements HttpRequest {
    private static final Logger logger = Logger.getLogger(GenericHttpRequest.class);

    private ApplicationConfiguration configuration;

    public GenericHttpRequest(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String doGet(String url) throws HttpException {
        return makeRequest(Method.GET, url);
    }

    @Override
    public String doPost(String url, String body) throws HttpException {
        return makeRequest(Method.POST, url, body);
    }

    @Override
    public String doPut(String url, String body) throws HttpException {
        return makeRequest(Method.PUT, url, body);
    }

    @Override
    public String doDelete(String url, String body) throws HttpException {
        return makeRequest(Method.DELETE, url, body);
    }

    private String makeRequest(Method method, String urlAddress) throws HttpException {
        return makeRequest(method, urlAddress, null);
    }

    private String makeRequest(Method method, String urlAddress, String body) throws HttpException {
        try {
            logger.info("Sending request " + method + " " + urlAddress);
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.name());
            //todo set headers explicitly
            connection.setRequestProperty("User-Agent", configuration.getUserAgent());
            connection.setRequestProperty("Content-Type", "application/json");
            if (null != body && !body.isEmpty()) {
                connection.setDoOutput(true);
                try (OutputStreamWriter writer = new OutputStreamWriter(
                    connection.getOutputStream(), configuration.getDefaultCharset())) {
                    writer.write(body);
                    writer.flush();
                }
            }
            //todo process response code advanced
            int responseCode = connection.getResponseCode();
            if (299 < responseCode) {
                throw new HttpException(
                    responseCode,
                    "Unexpected response code " + responseCode + " for request " + method + " " + urlAddress,
                    getContent(connection));
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
            logger.debug("Content has been retrieved: " + builder);
            return builder.toString();
        } catch (IOException e) {
            throw new HttpException("Exception caught while retrieving content from http connection", e);
        }
    }
}
