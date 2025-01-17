package com.stacktips.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartEvent {

    private final JobLauncher jobLauncher;
    private final Job csvImporterJob;

    @EventListener(ApplicationReadyEvent.class)
    public void onReadyEvent() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("ignoreCountry", "India")
                .addLong("startTime",System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(csvImporterJob, jobParameters);
    }
}