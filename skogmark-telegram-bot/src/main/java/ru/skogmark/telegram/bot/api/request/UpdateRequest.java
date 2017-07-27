package ru.skogmark.telegram.bot.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request params for getUpdates API method
 *
 * @author svip
 *         2017-07-28
 */
public class UpdateRequest {
    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("timeout")
    private Integer timeout;

    @JsonProperty("allowed_updates")
    private String[] allowedUpdates;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String[] getAllowedUpdates() {
        return allowedUpdates;
    }

    public void setAllowedUpdates(String[] allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
    }
}
