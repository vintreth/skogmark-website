package ru.skogmark.go.blogger.blog.telegram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;
import ru.skogmark.go.blogger.config.Configuration;
import ru.skogmark.go.blogger.rest.HttpException;
import ru.skogmark.go.blogger.rest.HttpRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author ksbogdan
 *         20.12.2016 15:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ContextConfiguration(locations = "classpath:beans.xml")
public class TelegramChannelBlogTest {
    private static final String REQUEST_URI = "https://api.telegram.org/bottestToken123456789/sendMessage";
    private static final String EXPECTED_MESSAGE_JSON = "{\"text\":\"Test content\",\"chat_id\":-1001099300337," +
            "\"parse_mode\":\"HTML\"," +
            "\"disable_web_page_preview\":false,\"disable_notification\":false}";

    @Autowired
    private TelegramConfiguration telegramConfiguration;

    @Autowired
    private TelegramBotToken telegramBotToken;

    @Test
    public void testRequestParams() throws Exception {
        HttpRequest httpRequest = getHttpRequest();
        Blog blog = getBlog(httpRequest);
        blog.post(getPost());
        
        verify(httpRequest, times(1)).doPost(REQUEST_URI, EXPECTED_MESSAGE_JSON);
    }

    private Blog getBlog(HttpRequest httpRequest) throws HttpException {
        TelegramChannelBlog blog = new TelegramChannelBlog(httpRequest, telegramConfiguration, telegramBotToken);

        return spy(blog);
    }

    private HttpRequest getHttpRequest() throws HttpException {
        HttpRequest httpRequest = mock(HttpRequest.class);
        when(httpRequest.doPost("", "")).thenReturn("ok");

        return httpRequest;
    }

    private Post getPost() {
        Post post = new Post();
        post.setContent("Test content");

        return post;
    }
}