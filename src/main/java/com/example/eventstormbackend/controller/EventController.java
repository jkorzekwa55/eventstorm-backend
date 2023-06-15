package com.example.eventstormbackend.controller;

import com.example.eventstormbackend.dto.EventDto;
import com.example.eventstormbackend.dto.EventPostDto;
import com.example.eventstormbackend.dto.EventWithDeclarationDto;
import com.example.eventstormbackend.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
@AllArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping
    public List<EventDto> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/extended")
    public List<EventWithDeclarationDto> getEventsWithDeclarations(Authentication authentication) {
        return eventService.getEventsWithDeclarations(authentication);
    }

    @GetMapping("/extended/{id}")
    public ResponseEntity<EventWithDeclarationDto> getEventsWithDeclarationById(
            Authentication authentication, @PathVariable Long id) {
        return eventService.getEventByIdWithDEclaration(authentication, id)
                .map(ResponseEntity::ok)
                .orElse(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(null);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto addEvent(@RequestBody EventPostDto eventPostDto,
                             Authentication authentication) {
        return eventService.addEvent(eventPostDto, authentication);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id,
                            Authentication authentication) {
        eventService.delete(id, authentication);
    }

}
