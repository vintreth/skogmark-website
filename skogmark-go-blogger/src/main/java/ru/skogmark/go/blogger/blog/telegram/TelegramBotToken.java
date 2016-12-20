package ru.skogmark.go.blogger.blog.telegram;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svip
 *         2016-12-18
 */
@XmlRootElement
class TelegramBotToken {
    private String value;

    public String getValue() {
        return value;
    }

    @XmlElement
    public void setValue(String value) {
        this.value = value;
    }
}
