package ru.skogmark.www.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skogmark.www.domain.User;

/**
 * @author svip
 *         2016-07-11
 */
@Repository
@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User getByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));

        return (User) criteria.uniqueResult();
    }

}
