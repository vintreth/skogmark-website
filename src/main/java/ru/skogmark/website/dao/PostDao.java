package ru.skogmark.website.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import ru.skogmark.website.domain.Post;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kbogdanov 14.03.16
 */
public class PostDao {

    @Resource
    private SessionFactory sessionFactory;

    public List<Post> getRecentPosts() {
        List<Post> recentPosts = new ArrayList<>();
        DetachedCriteria criteria = DetachedCriteria.forClass(Post.class);


        return recentPosts;
    }

    private List<Post> getAllByCriteria(DetachedCriteria criteria) {
        return new ArrayList<>();
    }

}
