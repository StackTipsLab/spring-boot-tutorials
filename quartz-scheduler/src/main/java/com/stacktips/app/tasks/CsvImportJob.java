package com.stacktips.app.tasks;

import com.opencsv.exceptions.CsvException;
import com.stacktips.app.service.ImportService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CsvImportJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(CsvImportJob.class);
    public final ImportService importService;

    public CsvImportJob(ImportService importService) {
        this.importService = importService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String param = dataMap.getString("param");

            log.info("CsvImportJob started with param: {}", param);
            importService.readBooks();

        } catch (IOException e) {
            log.error("IOException thrown while running job", e);
            throw new RuntimeException(e);
        } catch (CsvException e) {
            log.error("Exception thrown while running job", e);
            throw new RuntimeException(e);
        }
    }
}
