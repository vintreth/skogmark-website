package ru.skogmark.go.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.domain.RoleId;
import ru.skogmark.go.domain.SentencePart;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SwEEp on 06.01.2017.
 */
@Repository
@Transactional
public class SentencePartDao {
    private static final Logger logger = Logger.getLogger(SentencePartDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public SentencePartDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<SentencePart> getAll() {
        logger.debug("Retrieving all sentence parts from db");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SentencePart.class);
        criteria.addOrder(Order.asc("id"));

        return (List<SentencePart>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<SentencePart> getAllByRoleId(RoleId roleId) {
        logger.debug("Retrieving all sentence parts by roleId " + roleId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SentencePart.class);
        criteria.add(Restrictions.eq("role.id", roleId.value));
        criteria.addOrder(Order.asc("id"));

        return (List<SentencePart>) criteria.list();
    }

    public SentencePart getRandomByRoleId(RoleId roleId) {
        logger.debug("Retrieving random sentence part by roleId " + roleId);
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from SentencePart where role_id = ? order by RAND()");
        query.setParameter(0, roleId.value);
        query.setMaxResults(1);

        return (SentencePart) query.uniqueResult();
    }
}
