package ru.skogmark.go.gen.core.dao;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.gen.core.domain.Gender;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.SentenceRole;
import ru.skogmark.go.gen.core.domain.Tense;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;

@Repository
public class SentenceDao {
    private static final String FIELDS = "id, creator_id, date_created, content, role, gender, tense";
    private static final Logger log = LoggerFactory.getLogger(SentenceDao.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SentenceDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    public Sentence getRandomByRole(SentenceRole sentenceRole) {
        log.info("Finding random sentence: role={}", sentenceRole);
        String sql = "select " + FIELDS + " from sentence " +
                "where role = :role " +
                "order by random() " +
                "limit 1";
        Sentence sentence = jdbcTemplate.queryForObject(sql, ImmutableMap.of("role", sentenceRole.value),
                new SentenceRowMapper());
        log.info("Found random sentence: sentence={}", sentence);
        return sentence;
    }

    @Nullable
    public Sentence getRandomByRoleAndGender(SentenceRole sentenceRole, Gender gender) {
        log.info("Finding random sentence: role={}, gender={}", sentenceRole, gender);
        String sql = "select " + FIELDS + " from sentence " +
                "where role = :role " +
                "and gender = :gender " +
                "order by random() " +
                "limit 1";
        Sentence sentence = jdbcTemplate.queryForObject(sql,
                ImmutableMap.of("role", sentenceRole.value, "gender", gender.value), new SentenceRowMapper());
        log.info("Found sentence: sentence={}", sentence);
        return sentence;
    }

    @Nullable
    public Sentence getRandomByRoleAndTense(SentenceRole sentenceRole, Tense tense) {
        log.info("Finding random sentence: role={}, tense={}", sentenceRole, tense);
        String sql = "select " + FIELDS + " from sentence " +
                "where role = :role " +
                "and tense = :tense " +
                "order by random() " +
                "limit 1";
        Sentence sentence = jdbcTemplate.queryForObject(sql,
                ImmutableMap.of("role", sentenceRole.value, "tense", tense.value), new SentenceRowMapper());
        log.info("Found sentence: sentence={}", sentence);
        return sentence;
    }

    @Nullable
    public Sentence getById(long id) {
        log.info("Finding sentence by: id={}", id);
        String sql = "select " + FIELDS + " from sentence where id = :id";
        Sentence sentence = jdbcTemplate.queryForObject(sql, ImmutableMap.of("id", id), new SentenceRowMapper());
        log.info("Found sentence: sentence={}", sentence);
        return sentence;
    }

    private static class SentenceRowMapper implements RowMapper<Sentence> {
        @Override
        public Sentence mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sentence sentence = new Sentence();
            sentence.setId(rs.getLong("id"));
            sentence.setCreatorId(rs.getLong("creator_id"));
            sentence.setDateCreated(rs.getTimestamp("date_created").toLocalDateTime());
            sentence.setContent(rs.getString("content"));
            sentence.setRole(SentenceRole.getByValue(rs.getInt("role")).orElse(NONE));
            sentence.setGender(Gender.getByValue(rs.getInt("gender")).orElse(null));
            sentence.setTense(Tense.getByValue(rs.getInt("tense")).orElse(null));
            return sentence;
        }
    }
}
