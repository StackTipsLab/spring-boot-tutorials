
package com.abc.eventful.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Performers {

    @JsonProperty("performer")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public Performer performer;
}
