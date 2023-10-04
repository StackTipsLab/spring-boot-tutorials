
package com.abc.eventful.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EventsResponse {

    @JsonProperty("last_item")
    public Object lastItem;

    @JsonProperty("total_items")
    public String totalItems;

    @JsonProperty("first_item")
    public Object firstItem;

    @JsonProperty("page_number")
    public String pageNumber;

    @JsonProperty("page_size")
    public String pageSize;

    @JsonProperty("page_items")
    public Object pageItems;

    @JsonProperty("search_time")
    public String searchTime;

    @JsonProperty("page_count")
    public String pageCount;

    @JsonProperty("events")
    public Events events;
}
