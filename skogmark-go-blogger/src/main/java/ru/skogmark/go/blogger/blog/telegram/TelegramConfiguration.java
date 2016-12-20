package ru.skogmark.go.blogger.blog.telegram;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ksbogdan
 *         20.12.2016 11:00
 */
@XmlRootElement
class TelegramConfiguration {
    @XmlElement
    private int chatId;

    @XmlElement
    private Api api;

    public static class Api {
        @XmlElement
        private String url;

        @XmlElement
        private Methods methods;

        public String getUrl() {
            return url;
        }

        public Methods getMethods() {
            return methods;
        }
    }

    public static class Methods {
        @XmlElement
        private String sendMessage;

        public String getSendMessage() {
            return sendMessage;
        }
    }

    public int getChatId() {
        return chatId;
    }

    public Api getApi() {
        return api;
    }
}
