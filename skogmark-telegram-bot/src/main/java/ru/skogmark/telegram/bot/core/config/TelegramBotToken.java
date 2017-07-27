package ru.skogmark.telegram.bot.core.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svip
 * 2016-12-18
 */
@XmlRootElement
public class TelegramBotToken {
    @XmlAttribute
    private String value;

    public String getValue() {
        return value;
    }
}
