package ru.skogmark.go.gen.core.dao;

import com.google.common.collect.ImmutableMap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.gen.core.domain.ConjunctionType;
import ru.skogmark.go.gen.core.domain.old.Conjunction;
import ru.skogmark.go.gen.core.domain.old.RoleId;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by SwEEp on 06.01.2017.
 */
@Repository
public class ConjunctionDao {
    private static final String FIELDS = "id, creator_id, date_created, content, type";
    private static final Logger log = LoggerFactory.getLogger(ConjunctionDao.class);

    private final SessionFactory sessionFactory;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ConjunctionDao(SessionFactory sessionFactory, NamedParameterJdbcTemplate jdbcTemplate) {
        this.sessionFactory = sessionFactory;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Conjunction getRandomByRoleId(RoleId roleId) {
        log.debug("Retrieving random conjunction by getRoleId " + roleId);
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from Conjunction where role_id = ? order by RAND()");
        query.setParameter(0, roleId.value);
        query.setMaxResults(1);

        return (Conjunction) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Conjunction> getAll() {
        log.debug("Retrieving all conjunctions from db");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Conjunction.class);
        criteria.addOrder(Order.asc("id"));

        return (List<Conjunction>) criteria.list();
    }

    @Nullable
    public Conjunction getRandomByType(ConjunctionType conjunctionType) {
        log.info("Finding random conjunction: type={}", conjunctionType);
        String sql = "select " + FIELDS + " from conjunction " +
                "where type = :type " +
                "order by random() " +
                "limit 1";
        Conjunction conjunction = jdbcTemplate.queryForObject(sql, ImmutableMap.of("type", conjunctionType.value),
                new ConjunctionRowMapper());
        log.info("Found random conjunction: conjunction={}", conjunction);
        return conjunction;
    }

    @Nullable
    public Conjunction getById(long id) {
        log.info("Finding conjunction by: id={}", id);
        String sql = "select " + FIELDS + " from conjunction where id = :id";
        Conjunction conjunction = jdbcTemplate.queryForObject(sql, ImmutableMap.of("id", id),
                new ConjunctionRowMapper());
        log.info("Found conjunction: conjunction={}", conjunction);
        return conjunction;
    }

    private static class ConjunctionRowMapper implements RowMapper<Conjunction> {
        @Override
        public Conjunction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Conjunction conjunction = new Conjunction();
            conjunction.setId(rs.getLong("id"));
            conjunction.setCreatorId(rs.getLong("creator_id"));
            conjunction.setDateCreated(rs.getDate("date_created"));
            conjunction.setContent(rs.getString("content"));
            conjunction.setType(ConjunctionType.getByValue(rs.getInt("type")).orElse(null));
            return conjunction;
        }
    }
}
