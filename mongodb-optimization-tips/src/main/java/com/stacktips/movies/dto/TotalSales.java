package com.stacktips.movies.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TotalSales {
    private String productName;
    private double totalSales;
}
