package ru.skogmark.go.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.skogmark.go.domain.Conjunction;
import ru.skogmark.go.domain.RoleId;

import javax.transaction.Transactional;

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
                .createQuery("FROM Conjunction WHERE role_id = " + roleId.value + " ORDER BY RAND()");
        query.setMaxResults(1);

        return (Conjunction) query.uniqueResult();
    }
}
