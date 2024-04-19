package com.stacktips.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String headline;
    private String thumbnail;
    private String language;
    private String region;

    @Enumerated(EnumType.STRING)
    private ContentRating rating;

    @ManyToMany
    Set<Actor> actors;
}