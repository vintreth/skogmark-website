package ru.skogmark.telegram.bot.core.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TelegramBotSettings {
    private boolean localMode;

    public boolean isLocalMode() {
        return localMode;
    }

    @XmlElement
    public void setLocalMode(boolean localMode) {
        this.localMode = localMode;
    }
}
