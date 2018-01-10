package ru.skogmark.telegram.bot.core.update;

interface UpdateEventConsumer {
    void start();

    void stop();
}
