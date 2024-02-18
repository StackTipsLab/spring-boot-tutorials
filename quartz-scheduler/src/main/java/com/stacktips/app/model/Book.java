package com.stacktips.app.model;

public record Book(

        String id,
        String isbn,
        String isbn13,
        String authors,
        String publicationYear,
        String title,
        String languageCode,
        Double averageRating,
        String imageUrl) {
}
