package ru.skogmark.telegram.bot.core.update;

import ru.skogmark.telegram.bot.api.dto.Update;

public interface UpdateEventListener {
    void onUpdateEvent(Update update);
}
