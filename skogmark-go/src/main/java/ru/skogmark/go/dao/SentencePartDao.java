package ru.skogmark.go.dao;

import org.hibernate.Criteria;
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
    private SessionFactory sessionFactory;

    @Autowired
    public SentencePartDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<SentencePart> getAllByRoleId(RoleId roleId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SentencePart.class);
        criteria.add(Restrictions.eq("role.id", roleId.value));
        criteria.addOrder(Order.asc("id"));

        return (List<SentencePart>) criteria.list();
    }
}