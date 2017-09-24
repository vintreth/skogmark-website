package ru.skogmark.go.blogger.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;
import ru.skogmark.go.blogger.rest.service.WisdomService;

import java.util.Calendar;

import static org.mockito.Mockito.*;

/**
 * @author svip
 *         2016-12-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class PostSchedulerTest {
    @Autowired
    private ApplicationConfiguration configuration;

    @Test
    public void testTheTimeToPost() throws Exception {
        configure();
        WisdomService wisdomService = getWisdomService();
        PostScheduler postScheduler = new PostScheduler(getBlog(), configuration, wisdomService);
        postScheduler.beABlogger();
        verify(wisdomService, times(1)).getWisdom();
    }

    @Test
    public void testNoRepeatPost() throws Exception {
        configure();
        WisdomService wisdomService = getWisdomService();
        PostScheduler postScheduler = new PostScheduler(getBlog(), configuration, wisdomService);
        postScheduler.beABlogger();
        postScheduler.beABlogger();
        verify(wisdomService, times(1)).getWisdom();
    }

    private void configure() {
        int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        configuration.getPostSchedulerParams().getTimeTable().setTimes(
            new Integer[] {actualHour, actualHour + 1, actualHour + 10});
    }

    private WisdomService getWisdomService() throws Exception {
        WisdomService wisdomService = mock(WisdomService.class);
        when(wisdomService.getWisdom()).thenReturn(getWisdom());

        return wisdomService;
    }

    private Wisdom getWisdom() {
        Wisdom wisdom = new Wisdom();
        wisdom.setContent("Test wisdom");

        return wisdom;
    }

    private Blog getBlog() {
        return mock(Blog.class);
    }
}