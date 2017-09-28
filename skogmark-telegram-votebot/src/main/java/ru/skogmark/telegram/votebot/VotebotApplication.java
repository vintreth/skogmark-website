package ru.skogmark.telegram.votebot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.telegram.bot.AbstractBaseTelegramBotApplication;
import ru.skogmark.telegram.bot.TelegramBotApplication;

/**
 * @author svip
 *         2017-07-28
 */
// todo remove componentScan and replace it with explicit bean definitions
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ru.skogmark.telegram.bot"})
public class VotebotApplication extends AbstractBaseTelegramBotApplication {
    public static void main(String[] args) {
        TelegramBotApplication application = new VotebotApplication();
        application.start(VotebotApplication.class, args);
    }
}
