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
    private long chatId;

    @XmlElement
    private Api api;

    @XmlElement
    private String messageFormat;

    @XmlElement
    private ParseMode parseMode;

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

    public static class ParseMode {
        @XmlElement
        private String html;

        @XmlElement
        private String markdown;

        public String getHtml() {
            return html;
        }

        public String getMarkdown() {
            return markdown;
        }
    }

    public long getChatId() {
        return chatId;
    }

    public Api getApi() {
        return api;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public ParseMode getParseMode() {
        return parseMode;
    }
}