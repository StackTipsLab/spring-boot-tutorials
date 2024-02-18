package com.stacktips.app.api;

import com.stacktips.app.service.QuartzJobService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "quartz-jobs")
public class QuartzJobEndpoint {

    private static final Logger log = LoggerFactory.getLogger(QuartzJobEndpoint.class);
    private final QuartzJobService quartzJobService;

    public QuartzJobEndpoint(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    @WriteOperation
    public void manageJob(String action, String jobName, String jobGroup) {
        try {
            if ("start".equals(action)) {
                String triggerName = "csvImportInterruptableJobTrigger";
                quartzJobService.startJob(jobName, jobGroup, triggerName);
            } else if ("stop".equals(action)) {
                quartzJobService.stopJob(jobName, jobGroup);
            }
        } catch (SchedulerException e) {
            log.error("Error: {}", e.getMessage());
        }
    }
}