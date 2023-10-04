package com.abc.eventful.service;

import com.abc.eventful.client.EventfulClient;
import com.abc.eventful.model.Categories;
import com.abc.eventful.model.EventsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class EventfulServiceImpl implements EventfulService {

    private static final int DEFAULT_PAGE_SIZE = 48;

    private final EventfulClient client;

    @Value("${eventful.api.app-key}")
    private String appKey;

    @Override
    @Cacheable(value = "categories", unless = "#result == null")
    public Categories getCategories() {
        return client.categories(appKey);
    }

    @Override
    public EventsResponse getEvents(String location, String sortOrder) {
        return getEvents(appKey, location, sortOrder);
    }

    @Override
    @Cacheable(value = "events", key = "#location + #category + #sortOrder", unless = "#result == null")
    public EventsResponse getEvents(String category, String location, String sortOrder) {
        return client.search(appKey, location, category, sortOrder, DEFAULT_PAGE_SIZE);
    }
}
