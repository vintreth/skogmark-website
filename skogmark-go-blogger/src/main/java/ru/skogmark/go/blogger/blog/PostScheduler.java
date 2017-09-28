package ru.skogmark.go.blogger.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.blogger.client.GeneratorClient;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;

import java.util.Calendar;
import java.util.Date;

/**
 * @author svip
 * 2016-12-17
 */
@Component
public class PostScheduler {
    private static final Logger log = LoggerFactory.getLogger(PostScheduler.class);

    private final Blog blog;
    private final ApplicationConfiguration applicationConfiguration;
    private final GeneratorClient generatorClient;

    private Date postedDate;

    @Autowired
    public PostScheduler(Blog blog, ApplicationConfiguration applicationConfiguration,
                         GeneratorClient generatorClient) {
        this.blog = blog;
        this.applicationConfiguration = applicationConfiguration;
        this.generatorClient = generatorClient;
    }

    /**
     * Checks if a message need to be posted to a blog while starting application
     */
    public void checkPostWhileStarting() {
        log.debug("Checking if a message need to be posted while starting application");
        if (!applicationConfiguration.isPostWhileStartingEnabled()) {
            postedDate = new Date();
        }
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
        log.debug("Checking for time to post a message");
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
        todayCalendar.set(Calendar.MINUTE, 0);
        todayCalendar.set(Calendar.SECOND, 0);
        Date now = new Date();

        boolean timeSuitable = false;
        Integer[] timeTable = applicationConfiguration.getPostSchedulerParams().getTimeTable().getTimes();
        for (Integer hour : timeTable) {
            todayCalendar.set(Calendar.HOUR_OF_DAY, hour);
            Date postDate = todayCalendar.getTime();
            if (!isPostedAlready(postDate) && isTimeSuitable(now, postDate)) {
                log.debug("It's about time");
                timeSuitable = true;
                retrieveAndPost();
            }
        }
        if (!timeSuitable) {
            log.debug("It's not the time to post something");
        }
    }

    private boolean isPostedAlready(Date postDate) {
        return null != postedDate && postDate.getTime() < postedDate.getTime();
    }

    private boolean isTimeSuitable(Date now, Date postDate) {
        long maxTaskDelayMs = applicationConfiguration.getPostSchedulerParams().getMaxTaskDelayMinutes() * 60 * 1000L;
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
        } catch (PostingException e) {
            log.error("Unable to post a message", e);
        }
    }

    /**
     * Retrieves wisdom from service and creating a post
     *
     * @return new post
     */
    private Post retrieve() {
        Wisdom wisdom = generatorClient.getRandomWisdom();
        log.debug("Creating the post: " + wisdom.getContent());
        Post post = new Post();
        post.setContent(wisdom.getContent());
        return post;
    }

    private void post(Post post) throws PostingException {
        blog.post(post);
    }
}
