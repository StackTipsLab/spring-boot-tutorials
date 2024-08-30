package com.stacktips.movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private String headline;
    private String thumbnail;
    private String language;
    private String region;
    private List<String> actors;
    private List<String> genres;
    private ContentRating rating;
}
