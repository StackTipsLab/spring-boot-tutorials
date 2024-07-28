package com.stacktips.movies.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "logs")
public class LogEntry {

    @Id
    private String id;
    private final String log;
}
