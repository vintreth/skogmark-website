package ru.skogmark.go.gen.core.migration;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MigrationServiceTest {
    @Test
    public void shouldReturnContentForMigrationFiles() throws Exception {
        String sentenceTableSql = MigrationService.getMigrationFileContent(MigrationService.SQL_SENTENCE_TABLE);
        assertNotNull(sentenceTableSql);
        assertFalse(sentenceTableSql.isEmpty());

        String conjunctionTableSql = MigrationService.getMigrationFileContent(MigrationService.SQL_CONJUNCTION_TABLE);
        assertNotNull(conjunctionTableSql);
        assertFalse(conjunctionTableSql.isEmpty());
    }
}