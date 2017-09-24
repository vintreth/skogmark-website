package ru.skogmark.go.blogger.blog.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;
import ru.skogmark.go.blogger.rest.HttpException;
import ru.skogmark.common.http.HttpRequest;

/**
 * Blog implementation for telegram channel
 */
@Component
public class TelegramChannelBlog implements Blog {
    private static final Logger logger = Logger.getLogger(TelegramChannelBlog.class);

    private HttpRequest httpRequest;
    private TelegramConfiguration telegramConfiguration;
    private TelegramBotToken telegramBotToken;

    @Autowired
    public TelegramChannelBlog(
            HttpRequest httpRequest,
            TelegramConfiguration telegramConfiguration,
            TelegramBotToken telegramBotToken) {
        this.httpRequest = httpRequest;
        this.telegramConfiguration = telegramConfiguration;
        this.telegramBotToken = telegramBotToken;
    }

    @Override
    public void post(Post post) throws TelegramPostingException {
        try {
            TelegramMessage message = createTelegramMessage(post);
            logger.info("Posting message: " + message.getText());
            httpRequest.doPost(createUrl(), createBody(message));
            logger.info("Posted");
        } catch (HttpException e) {
            throw new TelegramPostingException("Failure to post", e);
        }
    }

    private TelegramMessage createTelegramMessage(Post post) {
        TelegramMessage message = new TelegramMessage();
        message.setChatId(telegramConfiguration.getChatId());
        message.setText(String.format(telegramConfiguration.getMessageFormat(), post.getContent()));
        message.setParseMode(telegramConfiguration.getParseModeByName("html").getValue());

        return message;
    }

    private String createUrl() {
        return String.format(
                telegramConfiguration.getApi().getUrl(),
                telegramBotToken.getValue(),
                telegramConfiguration.getMethodByName("sendMessage").getValue());
    }

    private String createBody(TelegramMessage message) throws TelegramPostingException {
        try {
            logger.debug("Creating request body");
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new TelegramPostingException(
                    "Failure to create request body for message with text: " + message.getText(), e);
        }
    }
}
