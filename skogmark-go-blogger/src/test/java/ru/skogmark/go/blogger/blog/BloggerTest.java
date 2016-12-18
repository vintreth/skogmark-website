package ru.skogmark.go.blogger.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skogmark.go.blogger.config.Configuration;
import ru.skogmark.go.blogger.domain.Wisdom;
import ru.skogmark.go.blogger.service.WisdomService;

import java.util.Calendar;

import static org.mockito.Mockito.*;

/**
 * @author svip
 *         2016-12-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class BloggerTest {
    @Autowired
    private Blog blog;

    @Autowired
    private Configuration configuration;

    @Test
    public void testTheTimeToPost() throws Exception {
        configure();
        WisdomService wisdomService = getWisdomService();
        Blogger blogger = new Blogger(blog, configuration, wisdomService);
        blogger.beABlogger();
        //todo check wisdom
        verify(wisdomService, times(1)).getWisdom();
    }

    @Test
    public void testNoRepeatPost() throws Exception {
        configure();
        WisdomService wisdomService = getWisdomService();
        Blogger blogger = new Blogger(blog, configuration, wisdomService);
        blogger.beABlogger();
        blogger.beABlogger();

        verify(wisdomService, times(1)).getWisdom();
    }

    private void configure() {
        int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        configuration.getBloggerParams().getTimeTable().setTimes(
            new Integer[] {actualHour, actualHour + 1, actualHour + 10});
    }

    @Test
    public void testForgottenPost() throws Exception {
        int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        configuration.getBloggerParams().getTimeTable().setTimes(
            new Integer[] {actualHour - 3, actualHour - 2, actualHour - 1, actualHour, actualHour + 5});
        configuration.getBloggerParams().setMaxTaskDelayHours(4);
        WisdomService wisdomService = getWisdomService();
        Blogger blogger = new Blogger(blog, configuration, wisdomService);
        blogger.beABlogger();
        //todo check wisdom
        verify(wisdomService, times(1)).getWisdom();
    }

    @Test
    public void testDelay() throws Exception {
        int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        configuration.getBloggerParams().getTimeTable().setTimes(
            new Integer[] {actualHour - 5, actualHour, actualHour + 5});
        configuration.getBloggerParams().setMaxTaskDelayHours(4);
        WisdomService wisdomService = getWisdomService();
        Blogger blogger = new Blogger(blog, configuration, wisdomService);
        blogger.beABlogger();
        //todo check wisdom
        verify(wisdomService, times(1)).getWisdom();
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
}