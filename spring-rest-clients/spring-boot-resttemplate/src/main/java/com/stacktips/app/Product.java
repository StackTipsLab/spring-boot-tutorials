package com.stacktips.app;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String image;
    private double price;
    private String description;
    private String title;
    private String category;

}