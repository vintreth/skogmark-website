package ru.skogmark.go.gen.core.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.gen.core.domain.old.Conjunction;
import ru.skogmark.go.gen.core.domain.old.RoleId;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SwEEp on 06.01.2017.
 */
@Repository
@Transactional
public class ConjunctionDao {
    private static final Logger logger = Logger.getLogger(ConjunctionDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public ConjunctionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Conjunction getRandomByRoleId(RoleId roleId) {
        logger.debug("Retrieving random conjunction by getRoleId " + roleId);
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from Conjunction where role_id = ? order by RAND()");
        query.setParameter(0, roleId.value);
        query.setMaxResults(1);

        return (Conjunction) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Conjunction> getAll() {
        logger.debug("Retrieving all conjunctions from db");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Conjunction.class);
        criteria.addOrder(Order.asc("id"));

        return (List<Conjunction>) criteria.list();
    }
}
