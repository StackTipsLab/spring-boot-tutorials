package com.stacktips.app.service;

import com.stacktips.app.tasks.CsvImportInterruptableJob;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class QuartzJobService {

    private static final String CRON_EXPRESSION = "0/5 * * * * ?";

    private final Scheduler scheduler;

    public QuartzJobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob(String jobName, String jobGroup, String triggerName) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(CsvImportInterruptableJob.class)
                .withIdentity(jobName, jobGroup)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, jobGroup)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION))
                .build();

        scheduler.scheduleJob(job, trigger);
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
    }

    public void startJob(String jobName, String jobGroup, String triggerName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        if (!scheduler.checkExists(jobKey)) {
            scheduleJob(jobName, jobGroup, triggerName);
        } else {
            scheduler.triggerJob(jobKey);
        }
    }

    public void stopJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        scheduler.interrupt(jobKey);
        scheduler.deleteJob(jobKey);
    }

    public void updateJob(String triggerName, String newTriggerName, String jobName, String jobGroup) throws SchedulerException {

        Trigger newTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, jobGroup)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION))
                .build();

        // Replace the existing trigger with the new one
        scheduler.rescheduleJob(TriggerKey.triggerKey(newTriggerName, jobGroup), newTrigger);

        // To delete a job
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
    }


}
