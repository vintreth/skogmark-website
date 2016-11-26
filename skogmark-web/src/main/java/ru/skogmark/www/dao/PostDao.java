package ru.skogmark.www.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skogmark.www.domain.Post;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kbogdanov 14.03.16
 */
@Transactional
@Repository
public class PostDao {

    @Resource
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger("PostDao");

    private static final int RECENT_POST_LIMIT = 6;

    public Set<Post> getRecentPosts() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Post.class);
        criteria.addOrder(Order.desc("createdAt"));

        return new HashSet<>(getAllByCriteria(criteria, RECENT_POST_LIMIT));
    }

    @SuppressWarnings("unchecked")
    private List<Post> getAllByCriteria(DetachedCriteria detachedCriteria, int limit) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.setMaxResults(limit);

        return criteria.list();
    }

    public Post getById(int id) {
        logger.debug("Retrieving post " + id + " from database");
        Session session = sessionFactory.getCurrentSession();

        return session.get(Post.class, id);
    }
}
