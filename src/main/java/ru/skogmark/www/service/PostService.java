package ru.skogmark.www.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.skogmark.www.dao.PostDao;
import ru.skogmark.www.domain.Post;
import ru.skogmark.www.store.StoreManager;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kbogdanov 14.03.16
 */
@Service
public class PostService {

    @Resource
    private PostDao postDao;

    @Resource
    private StoreManager storeManager;

    private static Logger logger = Logger.getLogger("PostService");

    public Set<Post> getRecentPosts() {
        return postDao.getRecentPosts();
    }

    public Post getPostById(int id) {
        return postDao.getById(id);
    }

    public String getCreatedAtString(Post post) {
        SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy");

        return format.format(post.getCreatedAt());
    }

    public String getImageSrc(Post post) {
        return storeManager.getImageSrc(post.getImage());
    }
}
