package ru.skogmark.telegram.bot.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents an incoming update.
 * At most one of the optional parameters can be present in any given update.

 * @author svip
 *         2017-07-26
 */
public class UpdateResponse {
    @JsonProperty("update_id")
    private int updateId;


}
