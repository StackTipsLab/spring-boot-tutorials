package com.abc.eventful.dto;

import lombok.Data;

@Data
public class EventRequest {

    private String category;
    private String location;
    private String sortOrder;
}
