package ru.skogmark.go.blogger.blog.telegram;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skogmark.common.config.ConfigurationFactory;

/**
 * @author ksbogdan
 * 20.12.2016 11:11
 */
@Configuration
class BeanConfiguration {
    private static final String CONFIG_PATH = "telegram-config.xml";

    @Bean(name = "telegramConfiguration")
    public TelegramConfiguration getTelegramConfiguration(ConfigurationFactory configurationFactory) {
        return configurationFactory.loadConfiguration(TelegramConfiguration.class, CONFIG_PATH);
    }
}
