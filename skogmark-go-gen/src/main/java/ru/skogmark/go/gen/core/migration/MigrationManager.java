package ru.skogmark.go.gen.core.migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Component
public class MigrationManager {
    static final String SQL_SENTENCE_TABLE = "ru/skogmark/go/gen/sql/sentence.table.sql";
    static final String SQL_CONJUNCTION_TABLE = "ru/skogmark/go/gen/sql/conjunction.table.sql";

    private static final String[] migrationFiles = new String[]{
            SQL_SENTENCE_TABLE, SQL_CONJUNCTION_TABLE
    };

    private static final Logger log = LoggerFactory.getLogger(MigrationManager.class);

    private final TransactionTemplate transactionTemplate;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MigrationManager(TransactionTemplate transactionTemplate, NamedParameterJdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void applyMigrations() {
        log.info("Starting applying migrations to database");
        Arrays.stream(migrationFiles).forEach(this::migrate);
    }

    private void migrate(String migrationFile) {
        log.info("Applying migration: file={}", migrationFile);
        try {
            String sql = getMigrationFileContent(migrationFile);
//            jdbcTemplate.getJdbcOperations().execute(sql);
        } catch (IOException e) {
            log.warn("Failure to apply migration: file=" + migrationFile, e);
        }
    }

    static String getMigrationFileContent(String resourceFile) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder sql = new StringBuilder();
            String line;
            while (null != (line = reader.readLine())) {
                sql.append(line);
            }
            return sql.toString();
        }
    }
}
