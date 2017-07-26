package ru.skogmark.common.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(GenericHttpRequest.class);

    private String userAgent;
    private String defaultCharset = "utf-8";

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDefaultCharset() {
        return defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    @Override
    public String doGet(String url) {
        return makeRequest(Method.GET, url);
    }

    @Override
    public String doPost(String url, String body) {
        return makeRequest(Method.POST, url, body);
    }

    @Override
    public String doPut(String url, String body) {
        return makeRequest(Method.PUT, url, body);
    }

    @Override
    public String doDelete(String url, String body) {
        return makeRequest(Method.DELETE, url, body);
    }

    private String makeRequest(Method method, String urlAddress) {
        return makeRequest(method, urlAddress, null);
    }

    private String makeRequest(Method method, String urlAddress, String body) {
        try {
            log.info("Sending request " + method + " " + urlAddress);
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.name());
            //todo set headers explicitly
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setRequestProperty("Content-Type", "application/json");
            if (null != body && !body.isEmpty()) {
                connection.setDoOutput(true);
                try (OutputStreamWriter writer = new OutputStreamWriter(
                    connection.getOutputStream(), defaultCharset)) {
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

    private String getContent(HttpURLConnection connection) {
        log.debug("Retrieving content from http connection");
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream(), defaultCharset))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while (null != (line = reader.readLine())) {
                builder.append(line);
            }
            log.debug("Content has been retrieved: " + builder);
            return builder.toString();
        } catch (IOException e) {
            throw new HttpException("Exception caught while retrieving content from http connection", e);
        }
    }
}
