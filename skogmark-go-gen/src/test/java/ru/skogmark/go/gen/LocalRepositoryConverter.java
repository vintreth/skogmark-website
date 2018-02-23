package ru.skogmark.go.gen;

import org.junit.Test;
import ru.skogmark.go.gen.core.domain.SentenceRole;
import ru.skogmark.go.gen.core.domain.old.RoleId;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Script for converting old local-wisdom.txt file to sql
 */
public class LocalRepositoryConverter {
    private static final String LOCAL_WISDOM_FILE = "local-wisdom.txt";
    private static final String SENTENCE_DATA_SQL_FILE = "ru/skogmark/go/gen/sentence.data.sql";
    private static final String TEMPLATE_FILE = "ru/skogmark/go/gen/sentence.template.sql";
    private static final String OUTPUT_FILE = "sentence.table.sql";

    private static final String INSERT_QUERY = "INSERT INTO sentence (date_created, content, role) VALUES \r\n ";
    private static final String QUERY_VALUE_TEMPLATE = "('%s', '%s', %d)";
    private static final String CURRENT_DATETIME = "NOW()";

    private List<QueryValue> queryValues;

    @Test
    public void convertTxtToSql() throws Exception {
        String values = readContent(LOCAL_WISDOM_FILE).stream()
                .map(line -> createQueryValue(line).asString())
                .collect(Collectors.joining("\r\n,"));
        String content = processTemplate(values);
        writeContent(content);
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

    private String processTemplate(String dynamicContent) {
        String template = String.join("\r\n", readContent(TEMPLATE_FILE));
        return template + "\r\n" + INSERT_QUERY + dynamicContent + "\r\n;";
    }

    private static void writeContent(String content) throws IOException {
        System.out.println(content);
        File file = new File(System.getProperty("user.dir") + "/target/" + OUTPUT_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
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
            queryValues = readContent(SENTENCE_DATA_SQL_FILE).stream()
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
        return Optional.of(new QueryValue(null, matcher.group(2), matcher.group(3),
                getSentenceRoleByOldId(matcher.group(4)).value));
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

    private static SentenceRole getSentenceRoleByOldId(String oldId) {
        return getRoleIdByCode(Integer.parseInt(oldId))
                .map(roleId -> {
                    if (RoleId.COMPLEX.equals(roleId)) {
                        return SentenceRole.SUBJECT;
                    } else if (RoleId.COMPOUND.equals(roleId)) {
                        return SentenceRole.ACTION;
                    } else if (RoleId.ADVERBIAL.equals(roleId)) {
                        return SentenceRole.ADVERBIAL;
                    } else {
                        return null;
                    }
                }).orElse(SentenceRole.NONE);
    }

    private static Optional<RoleId> getRoleIdByCode(int code) {
        return Arrays.stream(RoleId.values())
                .filter(roleId -> roleId.value == code)
                .findFirst();
    }
}
