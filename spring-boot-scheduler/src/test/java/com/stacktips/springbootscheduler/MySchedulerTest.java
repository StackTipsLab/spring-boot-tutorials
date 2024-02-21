package com.stacktips.springbootscheduler;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(AppConfig.class)
class MySchedulerTest {

    @SpyBean
    MyScheduler scheduler;

    @Test
    void test_startTask() {
        Awaitility.await()
                .atMost(7, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    verify(scheduler, atLeast(2)).startTask();
                });
    }
}