package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blogger;

/**
 * Scheduler for posts of blog
 */
@Component
class PostScheduler implements Runnable {
    private static final Logger logger = Logger.getLogger(PostScheduler.class);

    private Blogger blogger;

    @Autowired
    public PostScheduler(Blogger blogger) {
        this.blogger = blogger;
    }

    @Override
    public void run() {
        logger.debug("Doing job");
        blogger.beABlogger();
        logger.debug("Done");
    }
}
