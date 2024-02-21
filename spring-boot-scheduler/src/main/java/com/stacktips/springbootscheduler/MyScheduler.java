package com.stacktips.springbootscheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyScheduler {

    @Async
    @Scheduled(fixedRate = 3, initialDelay = 1, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void startTask() {
        log.info("Task started!");
    }
}
