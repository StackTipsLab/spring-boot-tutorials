package com.stacktips.app.service;

import com.stacktips.app.config.ImporterConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportService {

    private final ImporterConfig importerConfig;

    public void importData() {
        log.info("Importing data {}", importerConfig.toString());

        //TODO import logic goes here
    }
}
