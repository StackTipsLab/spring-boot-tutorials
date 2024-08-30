package com.stacktips.movies.dto;

import com.stacktips.movies.models.ContentRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String title;
    private String headline;
    private String thumbnail;
    private String language;
    private String region;
    private List<String> actors;
    private List<String> genres;
    private ContentRating rating;
}
