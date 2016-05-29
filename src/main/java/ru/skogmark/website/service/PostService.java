package ru.skogmark.website.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skogmark.website.dao.PostDao;
import ru.skogmark.website.domain.Post;
import ru.skogmark.website.store.StoreManager;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    public List<Post> getRecentPosts() {

        return new ArrayList<>();
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
