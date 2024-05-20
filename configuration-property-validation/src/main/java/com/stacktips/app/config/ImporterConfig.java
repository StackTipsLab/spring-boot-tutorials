package com.stacktips.app.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Component
@Validated
@ConfigurationProperties(prefix = "importer.service")
public class ImporterConfig {

    @NotEmpty
    String filePath;

    @NotEmpty
    @Pattern(regexp = "\\.csv$|\\.txt$")
    String fileType;

    @Positive
    @Max(10)
    int threadPoolSize;
}