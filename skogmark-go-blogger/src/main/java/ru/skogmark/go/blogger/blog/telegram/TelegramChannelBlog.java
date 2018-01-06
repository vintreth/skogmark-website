package ru.skogmark.go.blogger.blog.telegram;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skogmark.go.blogger.blog.Blog;
import ru.skogmark.go.blogger.blog.Post;
import ru.skogmark.telegram.bot.api.dto.InlineKeyboardButton;
import ru.skogmark.telegram.bot.api.dto.InlineKeyboardMarkup;
import ru.skogmark.telegram.bot.api.dto.Message;

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
        Message message = createTelegramMessage(post);
        log.info("Posting message: " + message.getText());
        telegramClient.postMessage(message);
        log.info("Posted");
    }

    private Message createTelegramMessage(Post post) {
        Message message = new Message();
        message.setChatId(telegramSettings.getChatId());
        message.setText(String.format(MESSAGE_FORMAT, post.getContent()));
        message.setParseMode(HTML.value);

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton dislikeButton = new InlineKeyboardButton();
        dislikeButton.setText("\uD83D\uDC4E");
        dislikeButton.setCallbackData("dislike");

        InlineKeyboardButton likeButton = new InlineKeyboardButton();
        likeButton.setText("\uD83D\uDC4D");
        likeButton.setCallbackData("like");
        keyboard.setInlineKeyboard(ImmutableList.of(ImmutableList.of(dislikeButton, likeButton)));
        message.setReplyMarkup(keyboard);
        return message;
    }
}
