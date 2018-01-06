package ru.skogmark.telegram.bot.api;

public enum ParseMode {
    HTML("HTML"),
    MARKDOWN("Markdown");

    public final String value;

    ParseMode(String value) {
        this.value = value;
    }
}
