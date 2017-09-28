package ru.skogmark.telegram.bot;

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
     */
    void onStartUp();

    /**
     * Custom actions for current application implementations
     */
    void beforeStop();
}
