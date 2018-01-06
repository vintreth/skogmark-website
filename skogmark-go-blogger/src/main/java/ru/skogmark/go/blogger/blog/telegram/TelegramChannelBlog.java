package ru.skogmark.go.blogger.blog.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;

import static ru.skogmark.telegram.bot.api.ParseMode.HTML;

/**
 * Blog implementation for telegram channel
 */
@Component("telegramChannelBlog")
public class TelegramChannelBlog implements Blog {
    private static final String MESSAGE_FORMAT = "<b>%s</b>";
    private static final Logger log = LoggerFactory.getLogger(TelegramChannelBlog.class);

    private final TelegramSettings telegramSettings;
    private final TelegramClient telegramClient;

    @Autowired
    public TelegramChannelBlog(TelegramSettings telegramSettings, TelegramClient telegramClient) {
        this.telegramSettings = telegramSettings;
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
        message.setChatId(telegramSettings.getChatId());
        message.setText(String.format(MESSAGE_FORMAT, post.getContent()));
        message.setParseMode(HTML.value);
        return message;
    }
}
