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
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class LocalRepositoryConverter {
    private static final String LOCAL_WISDOM_FILE = "local-wisdom.txt";
    private static final String SENTENCE_DATE_SQL_FILE = "ru/skogmark/go/gen/sentence.data.sql";

    private static final String INSERT_QUERY = "INSERT INTO sentence (date_created, content, role) VALUES \r\n ";
    private static final String QUERY_VALUE_TEMPLATE = "('%s', '%s', %d)";
    private static final String CURRENT_DATETIME = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    private List<QueryValue> queryValues;

    /**
     * Script for converting old local-wisdom.txt file to sql
     */
    @Test
    public void convertTxtToSql() throws Exception {
        String values = readContent(LOCAL_WISDOM_FILE).stream()
                .map(line -> createQueryValue(line).asString())
                .collect(Collectors.joining("\r\n,"));
        String query = INSERT_QUERY + values + "\r\n;";
        System.out.println(query);
        // todo write file
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

    private QueryValue createQueryValue(String line) {
        return new QueryValue(null, CURRENT_DATETIME, line, getRoleBySentence(line));
    }

    private int getRoleBySentence(String sentence) {
        return getQueryValuesFromSentenceData().stream()
                .filter(queryValue -> queryValue.content.equals(sentence))
                .map(queryValue -> queryValue.role)
                .findFirst()
                .orElse(0);
    }

    private List<QueryValue> getQueryValuesFromSentenceData() {
        if (isNull(queryValues)) {
            queryValues = readContent(SENTENCE_DATE_SQL_FILE).stream()
                    .map(LocalRepositoryConverter::parseQueryValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
        return queryValues;
    }

    private static Optional<QueryValue> parseQueryValue(String queryValueString) {
        Matcher matcher = Pattern.compile("\\((\\d+)[,\\s]*([A-Z()]+)[,\\s]*'(.*)'[,\\s]*(\\d+)\\)").matcher(queryValueString);
        if (!matcher.find()) {
            return Optional.empty();
        }
        // todo тут надо перемапить айдишники
        return Optional.of(new QueryValue(null, matcher.group(2), matcher.group(3),
                Integer.parseInt(matcher.group(4))));
    }

    private static class QueryValue {
        final Integer creatorId;
        final String dateCreated;
        final String content;
        final int role;

        QueryValue(Integer creatorId, String dateCreated, String content, int role) {
            this.creatorId = creatorId;
            this.dateCreated = dateCreated;
            this.content = content;
            this.role = role;
        }

        String asString() {
            return String.format(QUERY_VALUE_TEMPLATE, dateCreated, content, role);
        }

        @Override
        public String toString() {
            return "QueryValue{" +
                    "creatorId=" + creatorId +
                    ", dateCreated='" + dateCreated + '\'' +
                    ", content='" + content + '\'' +
                    ", role=" + role +
                    '}';
        }
    }
}
