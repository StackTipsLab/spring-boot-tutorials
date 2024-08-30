package com.stacktips.movies.dto;

import com.stacktips.movies.models.ContentRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private ContentRating rating;
    private String language;
    private String[] genres;
    private String[] actors;

}
