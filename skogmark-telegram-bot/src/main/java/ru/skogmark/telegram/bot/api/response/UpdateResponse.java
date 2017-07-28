package ru.skogmark.telegram.bot.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.skogmark.telegram.bot.api.dto.Update;

import java.util.List;

/**
 * Response object for getUpdates method
 *
 * @author svip
 *         2017-07-29
 */
public class UpdateResponse {
    @JsonProperty("ok")
    private boolean ok;

    @JsonProperty("result")
    private List<Update> result;

    @Override
    public String toString() {
        return "{ok: " + ok + ", result: " + result + "}";
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Update> getResult() {
        return result;
    }

    public void setResult(List<Update> result) {
        this.result = result;
    }
}
