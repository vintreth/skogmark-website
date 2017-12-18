package ru.skogmark.go.gen;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalRepositoryConverter {
    private static final String LOCAL_WISDOM_FILE = "local-wisdom.txt";
    private static final String SENTENCE_DATE_SQL_FILE = "ru/skogmark/go/gen/sentence.data.sql";

    private static final String INSERT_QUERY = "INSERT INTO sentence (date_created, content, role) VALUES ";
    private static final String QUERY_VALUE_TEMPLATE = "('%s', '%s', %d)";
    private static final String CURRENT_DATETIME = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    /**
     * Script for converting old local-wisdom.txt file to sql
     */
    @Test
    public void convertTxtToSql() throws Exception {
        List<String> values = readContent(LOCAL_WISDOM_FILE).stream()
                .map(line -> createQueryValue(line, getRoleBySentence(line)))
                .collect(Collectors.toList());
//        System.out.println(values);
    }

    private static List<String> readContent(String filePath) {
        List<String> sentences = new ArrayList<>(1000);
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to resolve file path " + filePath, e);
        }
        return sentences;
    }

    private static String createQueryValue(String line, int role) {
        return String.format(QUERY_VALUE_TEMPLATE, CURRENT_DATETIME, line, role);
    }

    private static int getRoleBySentence(String sentence) {
        return readContent(SENTENCE_DATE_SQL_FILE).stream()
                // todo
                // нужно полное соответствие,
                // надо распарсить строку, извлечь content и сравнивать на equals
                // заодно нужен последний параметр - role, поэтому удобнее всего будет парсить и создавать объект
                .filter(queryValue -> queryValue.contains(sentence))
                .map(queryValue -> {
                    System.out.println(queryValue);
                    return 1;
                })
                .findFirst()
                .orElse(0);
    }
}
