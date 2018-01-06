package ru.skogmark.go.blogger.blog.telegram;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * todo remove this class
 *
 * @author ksbogdan
 * 20.12.2016 11:00
 */
@XmlRootElement
public class TelegramSettings {
    private long chatId;

    public long getChatId() {
        return chatId;
    }

    @XmlElement
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
