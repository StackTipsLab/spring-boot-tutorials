package com.stacktips.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootBatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBatchApplication.class, args);
    }

    private final JobLauncher jobLauncher;
    private final Job csvImporterJob;
    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {

//		JobParameters jobParameters= new JobParametersBuilder().addString("catalogVersion", "10").toJobParameters()
//		jobLauncher.run(csvImporterJob, jobParameters);
        jobLauncher.run(csvImporterJob, new JobParameters());
    }
}
