package com.example.eventstormbackend.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class MongoService {
    private TicketmasterClientAPI ticketmasterClientAPI;
    private ImportJsonService importJsonService;

    @PostConstruct
    void init() {
        try {
            String response = ticketmasterClientAPI.getEvents();
            importJsonService.insertIntoCollection(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
