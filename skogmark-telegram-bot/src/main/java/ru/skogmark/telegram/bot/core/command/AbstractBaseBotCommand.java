package ru.skogmark.telegram.bot.core.command;

import ru.skogmark.telegram.bot.api.dto.Update;
import ru.skogmark.telegram.bot.core.update.UpdateEventListener;

public abstract class AbstractBaseBotCommand implements BotCommand<Update>, UpdateEventListener {
    @Override
    public void onUpdateEvent(Update update) {
        execute(update);
    }
}
