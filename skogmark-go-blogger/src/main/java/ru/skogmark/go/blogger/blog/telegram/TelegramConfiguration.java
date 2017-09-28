package ru.skogmark.go.blogger.blog.telegram;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.NoSuchElementException;

/**
 * todo remove this class
 *
 * @author ksbogdan
 *         20.12.2016 11:00
 */
@Deprecated
@XmlRootElement
class TelegramConfiguration {
    private long chatId;
    private Api api;
    private String messageFormat;
    private ParseMode[] parseModes;

    public static class Api {
        private String url;
        private Method[] methods;

        public String getUrl() {
            return url;
        }

        public Method[] getMethods() {
            return methods;
        }

        @XmlElement
        public void setUrl(String url) {
            this.url = url;
        }

        @XmlElementWrapper(name = "methods")
        @XmlElement(name = "method")
        public void setMethods(Method[] methods) {
            this.methods = methods;
        }
    }

    public static class Method {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @XmlAttribute
        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute
        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ParseMode {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @XmlAttribute
        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute
        public void setValue(String value) {
            this.value = value;
        }
    }

    public Method getMethodByName(String name) {
        for (Method method : getApi().getMethods()) {
            if (name.equals(method.getName())) {
                return method;
            }
        }
        throw new NoSuchElementException("Unable to find method " + name);
    }

    public ParseMode getParseModeByName(String name) {
        for (ParseMode parseMode : getParseModes()) {
            if (name.equals(parseMode.getName())) {
                return parseMode;
            }
        }
        throw new NoSuchElementException("Unable to find parseMode " + name);
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

    public ParseMode[] getParseModes() {
        return parseModes;
    }

    @XmlElement
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    @XmlElement
    public void setApi(Api api) {
        this.api = api;
    }

    @XmlElement
    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    @XmlElementWrapper(name = "parseModes")
    @XmlElement(name = "parseMode")
    public void setParseModes(ParseMode[] parseModes) {
        this.parseModes = parseModes;
    }
}
