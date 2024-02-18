package com.stacktips.app.service;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.stacktips.app.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class ImportService {

    private static final Logger log = LoggerFactory.getLogger(ImportService.class);

    public void readBooks() throws IOException, CsvException {
        File file = new File("src/data/books.csv");
        log.info("Importer started!");

        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            final List<String[]> rows = csvReader.readAll();
            List<Book> books = rows.stream()
                    .skip(1)
                    .map(row -> new Book(row[0], row[1], row[2], row[3], row[4],
                            row[5], row[6], Double.parseDouble(row[7]), row[8]))
                    .toList();
            log.info("Imported {} books", books.size());
        }

        log.info("Importer completed!");
    }
}
