package ru.skogmark.blogger.post;

import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;
import ru.skogmark.go.blogger.blog.PostingException;
import ru.skogmark.go.blogger.client.GeneratorClient;
import ru.skogmark.go.blogger.config.BloggerSettings;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author svip
 * 2016-12-17
 */
@Service
public class PostScheduler {
    private static final Logger log = LoggerFactory.getLogger(PostScheduler.class);

    private final Set<Blog> blogs;
    private final BloggerSettings bloggerSettings;
    private final GeneratorClient generatorClient;
    private final ScheduledExecutorService executor;

    private Date postedDate;

    public PostScheduler(@Qualifier("telegramChannelBlog") Blog telegramChannelBlog,
                         BloggerSettings bloggerSettings,
                         GeneratorClient generatorClient,
                         @Qualifier("postSchedulerExecutor") ScheduledExecutorService executor) {
        this.blogs = ImmutableSet.of(telegramChannelBlog);
        this.bloggerSettings = bloggerSettings;
        this.generatorClient = generatorClient;
        this.executor = executor;
    }

    public void start() {
        checkPostWhileStarting();
        log.debug("Starting scheduled executor");
        executor.scheduleAtFixedRate(this::schedule, 0L,
                bloggerSettings.getPostSchedulerParams().getTaskIntervalSec(), TimeUnit.SECONDS);
    }

    public void stop() {
        try {
            log.info("Stopping the application. Awaiting termination.");
            executor.awaitTermination(bloggerSettings.getAwaitTerminationTimeoutSec(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("InterruptedException occurred while awaiting scheduler termination", e);
            Thread.currentThread().interrupt();
        } finally {
            if (!executor.isTerminated()) {
                log.warn("Scheduler termination timeout");
                executor.shutdownNow();
            }
        }
    }

    /**
     * Checks if a message need to be posted to a blog while starting application
     */
    private void checkPostWhileStarting() {
        log.debug("Checking if a message need to be posted while starting application");
        if (!bloggerSettings.isPostWhileStartingEnabled()) {
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
    private void schedule() {
        try {
            log.debug("Checking for time to post a message");
            Calendar todayCalendar = Calendar.getInstance();
            todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
            todayCalendar.set(Calendar.MINUTE, 0);
            todayCalendar.set(Calendar.SECOND, 0);
            Date now = new Date();

            boolean timeSuitable = false;
            Integer[] timeTable = bloggerSettings.getPostSchedulerParams().getTimeTable().getTimes();
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
        } catch (Exception e) {
            log.error("Exception caught while scheduling posts", e);
        }
    }

    private boolean isPostedAlready(Date postDate) {
        return null != postedDate && postDate.getTime() < postedDate.getTime();
    }

    private boolean isTimeSuitable(Date now, Date postDate) {
        long maxTaskDelayMs = bloggerSettings.getPostSchedulerParams().getMaxTaskDelayMinutes() * 60 * 1000L;
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

    /**
     * Post a message to all blogs
     *
     * @param post message
     */
    private void post(Post post) {
        blogs.forEach(blog -> blog.post(post));
    }
}
