package ru.skogmark.go.gen.core.dao;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.SentenceRole;

import javax.annotation.Nullable;
import java.util.Map;

import static ru.skogmark.go.gen.core.domain.SentenceRole.NONE;

@Repository
public class SentenceDao {
    private static final Logger log = LoggerFactory.getLogger(SentenceDao.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SentenceDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    public Sentence getRandomSentenceByRole(SentenceRole sentenceRole) {
        log.info("Finding random sentence: role={}", sentenceRole);
        String sql = "select id, creator_id, date_created, content, role from sentence " +
                "where role = :role " +
                "order by random() " +
                "limit 1";
        Map<String, Integer> paramMap = ImmutableMap.of("role", sentenceRole.value);
        Sentence foundSentence = jdbcTemplate.queryForObject(sql, paramMap, (resultSet, rowNum) -> {
            Sentence sentence = new Sentence();
            sentence.setId(resultSet.getLong("id"));
            sentence.setCreatorId(resultSet.getLong("creator_id"));
            sentence.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());
            sentence.setContent(resultSet.getString("content"));
            sentence.setRole(SentenceRole.getByValue(resultSet.getInt("role")).orElse(NONE));
            return sentence;
        });
        log.info("Found random sentence: sentence={}", foundSentence);
        return foundSentence;
    }
}
