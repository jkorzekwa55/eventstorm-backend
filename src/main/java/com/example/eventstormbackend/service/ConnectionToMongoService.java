package com.example.eventstormbackend.service;

import com.example.eventstormbackend.apiClient.TicketmasterClientAPI;
import com.example.eventstormbackend.entity.Event;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ConnectionToMongoService {
    private TicketmasterClientAPI ticketmasterClientAPI;
    private ImportJsonService importJsonService;
    private EventService eventService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void insertFromTicketmasterAPI() {
        try {
            String response = ticketmasterClientAPI.getEvents();
            importJsonService.insertIntoCollection(response);

            getEventFromMongoRepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getEventFromMongoRepository() {
        try {
            mongoTemplate.getCollection("events").find().forEach(document -> {
                String s = document.toJson();
                JsonObject eventJSON = (new JsonParser()).parse(s).getAsJsonObject();
                String name = String.valueOf(eventJSON.get("name"));
                JsonObject venue = eventJSON.getAsJsonObject("_embedded").getAsJsonArray("venues")
                        .getAsJsonArray().get(0).getAsJsonObject();
                String venueName = String.valueOf(venue.get("name"));
                String city = String.valueOf(venue.getAsJsonObject("city").get("name"));
                String venueAddress = String.valueOf(venue.getAsJsonObject("address").get("line1"));
                String postalCode = String.valueOf(venue.get("postalCode"));
                String startDateTime = String.valueOf(eventJSON.getAsJsonObject("dates").getAsJsonObject("start").get("dateTime"));
                Event event = new Event();
                event.setName(name);
                event.setCity(city);
                event.setPostalCode(postalCode);
                event.setVenueAddress(venueAddress);
                System.out.println(startDateTime);
                if(!Objects.equals(startDateTime, "null")){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(startDateTime.substring(1, startDateTime.length() - 2), formatter);
                    event.setStartDateTime(dateTime);
                }

                event.setVenueName(venueName);

                eventService.addEvent(event);
            });


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
