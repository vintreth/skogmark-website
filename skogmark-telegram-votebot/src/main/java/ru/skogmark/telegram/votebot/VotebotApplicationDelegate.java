package ru.skogmark.telegram.votebot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.telegram.bot.TelegramBotApplication;

/**
 * @author svip
 *         2017-07-28
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ru.skogmark.telegram.bot"})
public class VotebotApplicationDelegate {
    public static void main(String[] args) {
        TelegramBotApplication application = new TelegramBotApplication();
        application.start(VotebotApplicationDelegate.class, args);
    }
}
