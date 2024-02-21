package com.stacktips.movies.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    private String title;
    private String headline;
    private String language;
    private String region;
    private List<String> actors;
    private List<String> genres;

}
