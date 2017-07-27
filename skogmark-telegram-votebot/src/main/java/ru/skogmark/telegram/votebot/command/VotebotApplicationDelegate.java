package ru.skogmark.telegram.votebot.command;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skogmark.telegram.bot.TelegramBotApplication;

/**
 * @author svip
 *         2017-07-28
 */
@SpringBootApplication
public class VotebotApplicationDelegate {
    public static void main(String[] args) {
        TelegramBotApplication application = new TelegramBotApplication();
        application.start(args);
    }
}
