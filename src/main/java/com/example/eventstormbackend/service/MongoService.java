package com.example.eventstormbackend.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MongoService {
    private TicketmasterClientAPI ticketmasterClientAPI;
    private ImportJsonService importJsonService;


    @PostConstruct
    public void init() {
        try {
            String response = ticketmasterClientAPI.getEvents();
            importJsonService.insertIntoCollection(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
