package com.stacktips.app.tasks;

import com.opencsv.exceptions.CsvException;
import com.stacktips.app.service.ImportService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CsvImportInterruptableJob implements InterruptableJob {

    private static final Logger log = LoggerFactory.getLogger(CsvImportInterruptableJob.class);

    private final ImportService importService;

    public CsvImportInterruptableJob(ImportService importService) {
        this.importService = importService;
    }

    private volatile boolean toStop = false;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        while (!toStop) {

            try {
                JobDataMap dataMap = context.getJobDetail().getJobDataMap();
                String param = dataMap.getString("param");

                log.info("CsvImportInterruptableJob started with parameter: {}", param);
                importService.readBooks();

            } catch (IOException e) {
                log.error("IOException thrown while running job", e);
                throw new RuntimeException(e);
            } catch (CsvException e) {
                log.error("Exception thrown while running job", e);
                throw new RuntimeException(e);
            }

            if (Thread.interrupted()) {
                // Perform any cleanup tasks and terminate
                toStop = true;
            }
        }

    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        toStop = true;
    }
}
