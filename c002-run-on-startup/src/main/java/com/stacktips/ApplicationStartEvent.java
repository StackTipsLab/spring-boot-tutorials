package com.stacktips;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartEvent {

    @EventListener(ApplicationReadyEvent.class)
    public void onReadyEvent() {
        log.info("ApplicationStartEvent::onReadyEvent()");

    }
}
