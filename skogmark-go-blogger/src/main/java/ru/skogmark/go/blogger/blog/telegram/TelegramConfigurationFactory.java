package ru.skogmark.go.blogger.blog.telegram;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.go.blogger.config.ConfigurationFactory;
import ru.skogmark.go.blogger.config.ConfigurationLoadingException;

/**
 * @author ksbogdan
 *         20.12.2016 11:11
 */
@Configuration
class TelegramConfigurationFactory extends ConfigurationFactory {
    private static final String CONFIG_PATH = "telegram-config.xml";
    private static final String TOKEN_PATH = "telegram-bot-token.xml";

    @Bean(name = "telegramConfiguration")
    public TelegramConfiguration getTelegramConfiguration() throws ConfigurationLoadingException {
        return loadConfiguration(TelegramConfiguration.class, CONFIG_PATH);
    }

    @Bean(name = "telegramBotToken")
    public TelegramBotToken geTelegramBotToken() throws ConfigurationLoadingException {
        return loadConfiguration(TelegramBotToken.class, TOKEN_PATH);
    }
}
