package com.example.eventstormbackend.service;

import com.example.eventstormbackend.entity.Event;
import com.example.eventstormbackend.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}
