package ru.skogmark.telegram.bot.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object for making getUpdates request
 *
 * @author svip
 *         2017-07-26
 */
public class UpdateRequest {
    @JsonProperty
    private int offset;

    @JsonProperty
    private int limit;

    @JsonProperty
    private int timeout;

    @JsonProperty("allowed_updates")
    private String[] allowedUpdates;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String[] getAllowedUpdates() {
        return allowedUpdates;
    }

    public void setAllowedUpdates(String[] allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
    }
}
