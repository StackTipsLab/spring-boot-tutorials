package com.stacktips.movies.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@JacksonXmlRootElement(localName = "movies")
public class Movies {

    @JacksonXmlProperty(localName = "movie")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Movie> moviesList;

}
