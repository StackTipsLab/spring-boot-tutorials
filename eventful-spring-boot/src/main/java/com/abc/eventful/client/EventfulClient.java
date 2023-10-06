package com.abc.eventful.client;

import com.abc.eventful.model.Categories;
import com.abc.eventful.model.EventsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eventfulClient", url = "${eventful.api.base-url}", decode404 = true)
public interface EventfulClient {

    @GetMapping(value = "/json/events/search")
    EventsResponse search(
            @RequestParam(value = "app_key") String appKey,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "sort_order", required = false) String sortOrder,
            @RequestParam(value = "page_size", required = false) int pageSize);

    @GetMapping(value = "/json/categories/list")
    Categories categories(@RequestParam(value = "app_key") String appKey);
}