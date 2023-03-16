package com.example.eventstormbackend.service;

import com.example.eventstormbackend.dto.EventDto;
import com.example.eventstormbackend.dto.EventPostDto;
import com.example.eventstormbackend.entity.Event;
import com.example.eventstormbackend.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private ModelMapper modelMapper;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public EventDto addEvent(EventPostDto eventPostDto) {
        Event event = modelMapper.map(eventPostDto, Event.class);
        Event toReturn = eventRepository.save(event);
        return modelMapper.map(toReturn, EventDto.class);
    }

    public List<EventDto> getEvents() {
        List<Event> allEvents = eventRepository.findAllEvents();
        return modelMapper.map(allEvents, new TypeToken<List<EventDto>>() {
        }.getType());
    }

    public Optional<EventDto> getEventById(Long id) {
        return eventRepository.findById(id).map(event -> modelMapper.map(event, EventDto.class));
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
