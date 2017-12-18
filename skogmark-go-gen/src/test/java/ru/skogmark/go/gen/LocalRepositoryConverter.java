package ru.skogmark.go.gen;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocalRepositoryConverter {
    private static final String LOCAL_WISDOM_FILE = "local-wisdom.txt";

    /**
     * Script for converting old local-wisdom.txt file to sql
     */
    @Test
    public void convertTxtToSql() throws Exception {
        List<String> sentences = readContent();
        System.out.println(sentences);
    }

    private static List<String> readContent() throws IOException {
        List<String> sentences = new ArrayList<>(1000);
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(LOCAL_WISDOM_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
        }
        return sentences;
    }
}
