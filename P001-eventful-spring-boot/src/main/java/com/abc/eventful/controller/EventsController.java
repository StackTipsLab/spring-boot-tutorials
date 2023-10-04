package com.abc.eventful.controller;


import com.abc.eventful.model.Categories;
import com.abc.eventful.model.EventsResponse;
import com.abc.eventful.service.EventfulService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class EventsController {

    private static final String DEFAULT_SORT_ORDER = "popularity";

    private final EventfulService service;

    @GetMapping(value = {"/events/{location}/{category}"})
    public String events(Model model, @PathVariable String category, @PathVariable String location) {
        Categories categories = service.getCategories();
        EventsResponse eventsResponse = service.getEvents(category, location, DEFAULT_SORT_ORDER);
        model.addAttribute("events",
                (null != eventsResponse.getEvents()) ? eventsResponse.getEvents().getEvent() : Collections.emptyList());
        model.addAttribute("categories", categories.getCategoryList());
        return "pages/home";
    }

    @GetMapping(value = {"/events/{location}"})
    public String events(Model model, @PathVariable String location) {
        Categories categories = service.getCategories();
        EventsResponse eventsResponse = service.getEvents(location, DEFAULT_SORT_ORDER);
        model.addAttribute("events", (null != eventsResponse.getEvents()) ? eventsResponse.getEvents().getEvent() : Collections.emptyList());
        model.addAttribute("categories", categories.getCategoryList());
        return "pages/home";
    }

}
