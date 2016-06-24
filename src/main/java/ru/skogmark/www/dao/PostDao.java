package ru.skogmark.www.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import ru.skogmark.www.domain.Post;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kbogdanov 14.03.16
 */
@Transactional
public class PostDao {

    @Resource
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger("PostDao");

    public List<Post> getRecentPosts() {
        List<Post> recentPosts = new ArrayList<>();
        DetachedCriteria criteria = DetachedCriteria.forClass(Post.class);

        return recentPosts;
    }

    private List<Post> getAllByCriteria(DetachedCriteria criteria) {
        Session session = sessionFactory.getCurrentSession();

        return new ArrayList<>();
    }

    public Post getById(int id) {
        logger.debug("Retrieving post " + id + " from database");
        Session session = sessionFactory.getCurrentSession();

        return session.get(Post.class, id);
    }
}
