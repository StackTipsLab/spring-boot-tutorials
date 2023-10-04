
package com.abc.eventful.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Performer {

    @JsonProperty("creator")
    public String creator;

    @JsonProperty("linker")
    public String linker;

    @JsonProperty("name")
    public String name;

    @JsonProperty("url")
    public String url;

    @JsonProperty("id")
    public String id;

    @JsonProperty("short_bio")
    public String shortBio;
}
