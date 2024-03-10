package com.stacktips.movies.dto;

import com.stacktips.movies.models.ContentRating;

public record SearchRequest(
        ContentRating rating,
        String language,
        String genre) {
}
