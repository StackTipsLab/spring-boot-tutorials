package com.stacktips.movies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document(collation = "movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private String headline;
    private ZonedDateTime releaseDateTime;
    private String thumbnail;
    private String language;
    private String region;
    private List<Genre> genre;
    private MovieRatings rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public ZonedDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    public void setReleaseDateTime(ZonedDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public MovieRatings getRating() {
        return rating;
    }

    public void setRating(MovieRatings rating) {
        this.rating = rating;
    }
}
