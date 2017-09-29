package ru.skogmark.telegram.bot;

import org.springframework.context.ApplicationContext;

/**
 * Base interface of a telegram bot application
 */
public interface TelegramBotApplication {
    /**
     * Starts the application
     *
     * @param applicationClass main application class with main() method
     * @param args console arguments
     */
    void start(Class<?> applicationClass, String[] args);

    /**
     * Custom actions for current application implementations
     * By default this method will be call after application context created
     *
     * @param applicationContext created ApplicationContext
     */
    void onStartUp(ApplicationContext applicationContext);

    /**
     * Custom actions for current application implementations
     *
     * @param applicationContext created ApplicationContext
     */
    void beforeStop(ApplicationContext applicationContext);
}
