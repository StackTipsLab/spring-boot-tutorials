package com.abc.eventful.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Category {
    @JsonProperty("name")
    private String name;

    @JsonProperty("event_count")
    private String eventCount;

    @JsonProperty("id")
    private String id;
}
