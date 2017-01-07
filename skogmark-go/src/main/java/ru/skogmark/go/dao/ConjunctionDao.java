package ru.skogmark.go.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.domain.Conjunction;
import ru.skogmark.go.domain.RoleId;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SwEEp on 06.01.2017.
 */
@Repository
@Transactional
public class ConjunctionDao {
    private SessionFactory sessionFactory;

    @Autowired
    public ConjunctionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Conjunction getRandomByRoleId(RoleId roleId) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from Conjunction where role_id = " + roleId.value + " order by RAND()");
        query.setMaxResults(1);

        return (Conjunction) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Conjunction> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Conjunction.class);
        criteria.addOrder(Order.asc("id"));

        return (List<Conjunction>) criteria.list();
    }
}
