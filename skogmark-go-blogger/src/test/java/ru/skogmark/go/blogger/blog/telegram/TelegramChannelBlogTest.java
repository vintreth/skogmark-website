package ru.skogmark.go.blogger.blog.telegram;

//import ru.skogmark.go.blogger.rest.HttpException;
//import ru.skogmark.go.blogger.rest.HttpRequest;

/**
 * @author ksbogdan
 *         20.12.2016 15:22
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//@ContextConfiguration(locations = "classpath:beans.xml")
public class TelegramChannelBlogTest {
//    private static final String REQUEST_URI = "https://api.telegram.org/bottestToken123456789/sendMessage";
//
//    @Autowired
//    private TelegramSettings telegramConfiguration;
//
//    @Autowired
//    private TelegramBotToken telegramBotToken;
//
//    @Test
//    public void testRequestParams() throws Exception {
//        HttpRequest httpRequest = getHttpRequest();
//        Blog blog = getBlog(httpRequest);
//        Post post = getPost("test message");
//        blog.post(post);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String expectedMessageJson = objectMapper.writeValueAsString(getTelegramMessage(post));
//
//        verify(httpRequest, times(1)).doPost(REQUEST_URI, expectedMessageJson);
//    }
//
//    private Blog getBlog(HttpRequest httpRequest) throws HttpException {
//        TelegramChannelBlog blog = new TelegramChannelBlog(httpRequest, telegramConfiguration, telegramBotToken, telegramClient);
//
//        return spy(blog);
//    }
//
//    private HttpRequest getHttpRequest() throws HttpException {
//        HttpRequest httpRequest = mock(HttpRequest.class);
//        when(httpRequest.doPost("", "")).thenReturn("ok");
//
//        return httpRequest;
//    }
//
//    private Post getPost(String content) {
//        Post post = new Post();
//        post.setContent(content);
//
//        return post;
//    }
//
//    private TelegramMessage getTelegramMessage(Post post) {
//        TelegramMessage message = new TelegramMessage();
//        message.setChatId(telegramConfiguration.getChatId());
//        message.setText(String.format(telegramConfiguration.getMessageFormat(), post.getContent()));
//        message.setParseMode(telegramConfiguration.getParseModeByName("html").getValue());
//
//        return message;
//    }
}