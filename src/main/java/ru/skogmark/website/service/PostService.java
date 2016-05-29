package ru.skogmark.website.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skogmark.website.dao.PostDao;
import ru.skogmark.website.domain.Post;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kbogdanov 14.03.16
 */
@Service
public class PostService {

    @Resource
    private PostDao postDao;

    private static Logger logger = Logger.getLogger("PostService");

    public List<Post> getRecentPosts() {

        return new ArrayList<>();
    }

    public Post getPostById(int id) {
        return postDao.getById(id);
    }
}
