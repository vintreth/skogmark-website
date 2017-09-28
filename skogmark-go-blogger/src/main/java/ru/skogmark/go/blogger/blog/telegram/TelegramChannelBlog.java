package ru.skogmark.go.blogger.blog.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;

/**
 * Blog implementation for telegram channel
 */
@Component
public class TelegramChannelBlog implements Blog {
    private static final Logger log = LoggerFactory.getLogger(TelegramChannelBlog.class);

    private final TelegramConfiguration telegramConfiguration;
    private final TelegramClient telegramClient;

    @Autowired
    public TelegramChannelBlog(TelegramConfiguration telegramConfiguration, TelegramClient telegramClient) {
        this.telegramConfiguration = telegramConfiguration;
        this.telegramClient = telegramClient;
    }

    @Override
    public void post(Post post) {
        TelegramMessage message = createTelegramMessage(post);
        log.info("Posting message: " + message.getText());
        telegramClient.postMessage(message);
        log.info("Posted");
    }

    private TelegramMessage createTelegramMessage(Post post) {
        TelegramMessage message = new TelegramMessage();
        message.setChatId(telegramConfiguration.getChatId());
        message.setText(String.format(telegramConfiguration.getMessageFormat(), post.getContent()));
        message.setParseMode(telegramConfiguration.getParseModeByName("html").getValue());
        return message;
    }
}
