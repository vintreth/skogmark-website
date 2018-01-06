package ru.skogmark.telegram.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InlineKeyboardButton {
    private String text;
    private String url;
    private String callbackData;
    private String switchInlineQuery;
    private String switchInlineQueryCurrentChat;
    private boolean pay;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("callback_data")
    public String getCallbackData() {
        return callbackData;
    }

    @JsonProperty("callback_data")
    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    @JsonProperty("switch_inline_query")
    public String getSwitchInlineQuery() {
        return switchInlineQuery;
    }

    @JsonProperty("switch_inline_query")
    public void setSwitchInlineQuery(String switchInlineQuery) {
        this.switchInlineQuery = switchInlineQuery;
    }

    @JsonProperty("switch_inline_query_current_chat")
    public String getSwitchInlineQueryCurrentChat() {
        return switchInlineQueryCurrentChat;
    }

    @JsonProperty("switch_inline_query_current_chat")
    public void setSwitchInlineQueryCurrentChat(String switchInlineQueryCurrentChat) {
        this.switchInlineQueryCurrentChat = switchInlineQueryCurrentChat;
    }

    @JsonProperty("pay")
    public boolean isPay() {
        return pay;
    }

    @JsonProperty("pay")
    public void setPay(boolean pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "InlineKeyboardButton{" +
                "text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", callbackData='" + callbackData + '\'' +
                ", switchInlineQuery='" + switchInlineQuery + '\'' +
                ", switchInlineQueryCurrentChat='" + switchInlineQueryCurrentChat + '\'' +
                ", pay=" + pay +
                '}';
    }
}
