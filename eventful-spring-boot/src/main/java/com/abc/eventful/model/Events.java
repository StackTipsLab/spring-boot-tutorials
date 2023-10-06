
package com.abc.eventful.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {

    @JsonProperty("event")
    public List<Event> event;
}
