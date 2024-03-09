package com.stacktips.app.config;

import com.stacktips.app.tasks.CsvImportJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail csvImportJob() {
        return JobBuilder.newJob(CsvImportJob.class)
                .usingJobData("arg1", "Hello World!")
                .withIdentity("csvImportJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger csvImportJobTrigger(JobDetail csvImportJob) {
        return TriggerBuilder.newTrigger()
                .forJob(csvImportJob)
                .withIdentity("csvImportJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }



    // Commented out in favour of Cron based trigger

//    @Bean
//    public Trigger csvImportJobTrigger(JobDetail csvImportJob) {
//        Date afterFiveSeconds = Date.from(LocalDateTime.now().plusSeconds(5)
//                .atZone(ZoneId.systemDefault()).toInstant());
//
//        return TriggerBuilder.newTrigger()
//                .forJob(csvImportJob)
//                .startAt(afterFiveSeconds)
//                .withIdentity("simpleTrigger")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(60)
//                        .repeatForever())
//                .build();
//    }


}
