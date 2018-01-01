package ru.skogmark.go.gen.core.dao;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.gen.core.domain.Sentence;
import ru.skogmark.go.gen.core.domain.SentenceRole;

import java.util.Map;

@Repository
public class SentenceDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SentenceDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Sentence getRandomSentenceByRole(SentenceRole sentenceRole) {
        String sql = "select id, creator_id, date_created, content, role from sentence " +
                "where role = :role " +
                "order by rand()";
        Map<String, Integer> paramMap = ImmutableMap.of("role", sentenceRole.value);
        return jdbcTemplate.queryForObject(sql, paramMap, (resultSet, rowNum) -> {
            Sentence sentence = new Sentence();
            sentence.setId(resultSet.getLong("id"));
            sentence.setCreatorId(resultSet.getLong("creator_id"));
//            sentence.setDateCreated(LocalDateTime.ofEpochSecond(resultSet.getTimestamp("date_created").getTime(), ));
            sentence.setContent(resultSet.getString("content"));
//            sentence.setRole(resultSet.getInt("role"));
            return sentence;
        });
    }
}
