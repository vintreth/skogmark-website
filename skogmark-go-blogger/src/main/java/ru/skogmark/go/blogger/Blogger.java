package ru.skogmark.go.blogger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;
import ru.skogmark.go.blogger.config.Configuration;
import ru.skogmark.go.blogger.exception.FailPostRetrievingException;
import ru.skogmark.go.blogger.rest.HttpException;
import ru.skogmark.go.blogger.rest.HttpRequest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author svip
 *         2016-12-17
 */
@Component
class Blogger {
    private static final Logger logger = Logger.getLogger(Blogger.class);

    private Blog blog;
    private Configuration configuration;
    private HttpRequest httpRequest;

    private Date postedDate;

    @Autowired
    public Blogger(Blog blog, Configuration configuration, HttpRequest httpRequest) {
        this.blog = blog;
        this.configuration = configuration;
        this.httpRequest = httpRequest;
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

        Integer[] timeTable = configuration.getPostSchedulerParams().getTimeTable().getTimes();
        Arrays.asList(timeTable).forEach(hour -> {
            todayCalendar.set(Calendar.HOUR_OF_DAY, hour);
            Date postDate = todayCalendar.getTime();
            if (!isPostedAlready(postDate) && isTimeSuitable(now, postDate)) {
                logger.debug("It's about time");
                retrieveAndPost();
            }
        });
    }

    private boolean isPostedAlready(Date postDate) {
        return null != postedDate && postDate.getTime() < postedDate.getTime();
    }

    private boolean isTimeSuitable(Date now, Date postDate) {
        long maxJobDelayMs = configuration.getPostSchedulerParams().getMaxJobDelayHours() * 3600 * 1000L;
        return postDate.getTime() <= now.getTime() && now.getTime() < (postDate.getTime() + maxJobDelayMs);
    }

    /**
     * Shortcut for {@link #retrieveMessage()} and {@link #postMessage(Post)}
     */
    private void retrieveAndPost() {
        try {
            Post post = retrieveMessage();
            postMessage(post);
            postedDate = new Date();
        } catch (FailPostRetrievingException e) {
            logger.error("Unable to post a message", e);
        }
    }

    private Post retrieveMessage() throws FailPostRetrievingException {
        try {
            String content = httpRequest.doGet(configuration.getGeneratorResourceUrl());
            if (content.isEmpty()) {
                throw new FailPostRetrievingException("Empty content");
            }

        } catch (HttpException e) {
            throw new FailPostRetrievingException("Failure to retrieve a post message", e);
        }
        return null;
    }

    private void postMessage(Post post) {
        blog.post(post);
    }
}
