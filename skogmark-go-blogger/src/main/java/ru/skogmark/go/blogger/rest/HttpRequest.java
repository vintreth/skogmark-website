package ru.skogmark.go.blogger.rest;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
@Component
public class HttpRequest {
    public void doGet(String url) {
        makeRequest("GET", url);
    }

    private void makeRequest(String method, String urlAddress) {
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}
