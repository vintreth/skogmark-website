package ru.skogmark.go.blogger.blog;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.config.Configuration;
import ru.skogmark.go.blogger.rest.entity.Wisdom;
import ru.skogmark.go.blogger.rest.FailEntityRetrievingException;
import ru.skogmark.go.blogger.rest.service.WisdomService;

import java.util.Calendar;
import java.util.Date;

/**
 * @author svip
 *         2016-12-17
 */
@Component
public class PostScheduler {
    private static final Logger logger = Logger.getLogger(PostScheduler.class);

    private Blog blog;
    private Configuration configuration;
    private WisdomService wisdomService;

    private Date postedDate;

    @Autowired
    public PostScheduler(Blog blog, Configuration configuration, WisdomService wisdomService) {
        this.blog = blog;
        this.configuration = configuration;
        this.wisdomService = wisdomService;
    }

    /**
     * Check the current timetable.
     * <p>
     * Retrieves a message from generator.
     * <p>
     * Posts the message if it's about time
     * or the message hasn't been posted at necessary time.
     */
    public void beABlogger() {
        logger.debug("Checking for time to post a message");
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
        todayCalendar.set(Calendar.MINUTE, 0);
        todayCalendar.set(Calendar.SECOND, 0);
        Date now = new Date();

        boolean timeSuitable = false;
        Integer[] timeTable = configuration.getPostSchedulerParams().getTimeTable().getTimes();
        for (Integer hour : timeTable) {
            todayCalendar.set(Calendar.HOUR_OF_DAY, hour);
            Date postDate = todayCalendar.getTime();
            if (!isPostedAlready(postDate) && isTimeSuitable(now, postDate)) {
                logger.debug("It's about time");
                timeSuitable = true;
                retrieveAndPost();
            }
        }
        if (!timeSuitable) {
            logger.debug("It's not the time to post something");
        }
    }

    private boolean isPostedAlready(Date postDate) {
        return null != postedDate && postDate.getTime() < postedDate.getTime();
    }

    private boolean isTimeSuitable(Date now, Date postDate) {
        long maxTaskDelayMs = configuration.getPostSchedulerParams().getMaxTaskDelayHours() * 3600 * 1000L;
        return postDate.getTime() <= now.getTime() && now.getTime() < (postDate.getTime() + maxTaskDelayMs);
    }

    /**
     * Shortcut for {@link #retrieve()} and {@link #post(Post)}
     */
    private void retrieveAndPost() {
        try {
            Post post = retrieve();
            post(post);
            postedDate = new Date();
        } catch (FailEntityRetrievingException | PostingException e) {
            logger.error("Unable to post a message", e);
        }
    }

    /**
     * Retrieves wisdom from service and creating a post
     *
     * @return new post
     * @throws FailEntityRetrievingException if error occurred while getting wisdom
     */
    private Post retrieve() throws FailEntityRetrievingException {
        Wisdom wisdom = wisdomService.getWisdom();
        logger.debug("Creating the post: " + wisdom.getContent());
        Post post = new Post();
        post.setContent(wisdom.getContent());

        return post;
    }

    private void post(Post post) throws PostingException {
        blog.post(post);
    }
}
