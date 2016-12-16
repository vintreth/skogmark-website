package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Scheduler of posts of blog
 */
@Component
public class PostScheduler implements Runnable {
    private static final long MAX_DELAY_MS = 4 * 3600 * 1000L;
    private static final Logger logger = Logger.getLogger(PostScheduler.class);

    private Blog blog;

    /**
     * Time table for posting message to a blog
     */
    private Integer[] timeTable = new Integer[] {8, 12, 18};

    @Autowired
    public PostScheduler(Blog blog) {
        this.blog = blog;
    }

    @Override
    public void run() {
        beABlogger();
    }

    /**
     * Check the current timetable.
     * <p>
     * Retrieves a message from generator.
     * <p>
     * Posts the message if it's about time
     * or the message hasn't been posted at necessary time.
     */
    private void beABlogger() {
        logger.debug("Checking for time to post a message");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date now = new Date();

        Arrays.asList(timeTable).forEach(hour -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            Date postDate = calendar.getTime();
            if (postDate.getTime() <= now.getTime() && now.getTime() < (postDate.getTime() + MAX_DELAY_MS)) {
                logger.debug("It's about time");
                retrieveAndPost();
            }
        });
    }

    private void retrieveAndPost() {

    }

    private Post retrieveMessage() {

        return null;
    }

    private void postMessage(Post post) {
        blog.post(post);
    }
}
