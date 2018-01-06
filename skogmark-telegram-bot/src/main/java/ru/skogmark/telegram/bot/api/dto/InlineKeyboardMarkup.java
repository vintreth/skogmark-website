package ru.skogmark.telegram.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InlineKeyboardMarkup {
    private List<List<InlineKeyboardButton>> inlineKeyboard;

    @JsonProperty("inline_keyboard")
    public void setInlineKeyboard(List<List<InlineKeyboardButton>> inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
    }

    @JsonProperty("inline_keyboard")
    public List<List<InlineKeyboardButton>> getInlineKeyboard() {
        return inlineKeyboard;
    }
}
