package ru.skogmark.go.gen.core.dao;

import com.google.common.collect.ImmutableMap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.gen.core.domain.ConjunctionType;
import ru.skogmark.go.gen.core.domain.old.Conjunction;
import ru.skogmark.go.gen.core.domain.old.RoleId;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Created by SwEEp on 06.01.2017.
 */
@Repository
public class ConjunctionDao {
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
    public Conjunction getRandomConjunctionByType(ConjunctionType conjunctionType) {
        log.info("Finding random conjunction: type={}", conjunctionType);
        String sql = "select id, creator_id, date_created, content, type from conjunction " +
                "where type = :type " +
                "order by random() " +
                "limit 1";
        Map<String, Integer> paramMap = ImmutableMap.of("type", conjunctionType.value);
        Conjunction foundConjunction = jdbcTemplate.queryForObject(sql, paramMap, (resultSet, rowNum) -> {
            Conjunction conjunction = new Conjunction();
            conjunction.setId(resultSet.getLong("id"));
            conjunction.setCreatorId(resultSet.getLong("creator_id"));
            conjunction.setDateCreated(resultSet.getDate("date_created"));
            conjunction.setContent(resultSet.getString("content"));
            conjunction.setType(ConjunctionType.getByValue(resultSet.getInt("type")).orElse(null));
            return conjunction;
        });
        log.info("Found random conjunction: conjunction={}", foundConjunction);
        return foundConjunction;
    }
}
