package com.stacktips.movies.dto;

import com.stacktips.movies.models.ContentRating;

public class SearchRequest {

    private ContentRating rating;
    private String language;
    private String[] genres;
    private String[] actors;

    public ContentRating getRating() {
        return rating;
    }

    public void setRating(ContentRating rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
}
