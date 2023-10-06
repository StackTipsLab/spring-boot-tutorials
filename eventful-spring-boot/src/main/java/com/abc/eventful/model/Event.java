
package com.abc.eventful.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    @JsonProperty("watching_count")
    public long watchingCount;

    @JsonProperty("olson_path")
    public String olsonPath;

    @JsonProperty("calendar_count")
    public long calendarCount;

    @JsonProperty("comment_count")
    public long commentCount;

    @JsonProperty("region_abbr")
    public String regionAbbr;

    @JsonProperty("postal_code")
    public String postalCode;

    @JsonProperty("going_count")
    public long goingCount;

    @JsonProperty("all_day")
    public String allDay;

    @JsonProperty("latitude")
    public String latitude;

//    @JsonProperty("groups")
//    public Object groups;

    @JsonProperty("url")
    public String url;

    @JsonProperty("id")
    public String id;

    @JsonProperty("privacy")
    public String privacy;

    @JsonProperty("city_name")
    public String cityName;

    @JsonProperty("link_count")
    public long linkCount;

    @JsonProperty("longitude")
    public String longitude;

    @JsonProperty("country_name")
    public String countryName;

    @JsonProperty("country_abbr")
    public String countryAbbr;

    @JsonProperty("region_name")
    public String regionName;

    @JsonProperty("start_time")
    public String startTime;

//    @JsonProperty("tz_id")
//    public Object tzId;

    @JsonProperty("description")
    public String description;

    @JsonProperty("modified")
    public String modified;

    @JsonProperty("venue_display")
    public String venueDisplay;

//    @JsonProperty("tz_country")
//    public Object tzCountry;

//    @JsonProperty("performers")
//    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//    public Performers performers;

    @JsonProperty("title")
    public String title;

    @JsonProperty("venue_address")
    public String venueAddress;

    @JsonProperty("geocode_type")
    public String geocodeType;

    @JsonProperty("tz_olson_path")
    public String tzOlsonPath;

    @JsonProperty("recur_string")
    public Object recurString;

    @JsonProperty("calendars")
    public Object calendars;

    @JsonProperty("owner")
    public String owner;

    @JsonProperty("going")
    public Object going;

    @JsonProperty("country_abbr2")
    public String countryAbbr2;

    @JsonProperty("image")
    public Object image;

    @JsonProperty("created")
    public String created;

    @JsonProperty("venue_id")
    public String venueId;

    @JsonProperty("tz_city")
    public Object tzCity;

    @JsonProperty("stop_time")
    public Object stopTime;

    @JsonProperty("venue_name")
    public String venueName;

    @JsonProperty("venue_url")
    public String venueUrl;
}

