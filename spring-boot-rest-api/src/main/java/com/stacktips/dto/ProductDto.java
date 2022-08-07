package com.stacktips.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ProductDto {
    String title;
    Double price;
    String description;

    @JsonProperty("in_stock")
    boolean inStock;
    boolean active;

    @JsonProperty("units_available")
    Long unitsAvailable;
}
