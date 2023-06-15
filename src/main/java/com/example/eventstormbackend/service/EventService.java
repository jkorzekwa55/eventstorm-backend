package com.example.eventstormbackend.service;

import com.example.eventstormbackend.dto.EventDto;
import com.example.eventstormbackend.dto.EventPostDto;
import com.example.eventstormbackend.dto.EventWithDeclarationDto;
import com.example.eventstormbackend.entity.Event;
import com.example.eventstormbackend.entity.User;
import com.example.eventstormbackend.model.Role;
import com.example.eventstormbackend.repository.EventRepository;
import com.example.eventstormbackend.repository.UserEventRepository;
import com.example.eventstormbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;
    private UserEventRepository userEventRepository;
    private ModelMapper modelMapper;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public EventDto addEvent(EventPostDto eventPostDto, Authentication authentication) {
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if (user.isPresent()) {
            Event event = modelMapper.map(eventPostDto, Event.class);
            Event toReturn = eventRepository.save(event);
            return modelMapper.map(toReturn, EventDto.class);
        }
        return null;
    }

    public List<EventDto> getEvents() {
        List<Event> allEvents = eventRepository.findAllEvents();
        return modelMapper.map(allEvents, new TypeToken<List<EventDto>>() {
        }.getType());
    }

    public Optional<EventDto> getEventById(Long id) {
        return eventRepository.findById(id).map(event -> modelMapper.map(event, EventDto.class));
    }

    public void delete(Long id, Authentication authentication) {
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        Optional<Event> event = eventRepository.findById(id);
        if (user.isPresent() && event.isPresent()) {
            if (user.get().getRole() != Role.USER || (user.get().getRole() == Role.USER &&
                    event.get().getOwner() == user.get())) {
                eventRepository.deleteById(id);
            }
        }
    }

    public List<EventWithDeclarationDto> getEventsWithDeclarations(Authentication authentication) {
        if(authentication == null){
            List<Event> allEvents = eventRepository.findAllEvents();
            return allEvents.stream().map(o -> modelMapper.map(o, EventWithDeclarationDto.class)).toList();
        }else{
            Optional<User> user = userRepository.findByUsername(authentication.getName());
            List<Event> allEvents = eventRepository.findAllEvents();
            List<EventWithDeclarationDto> collect = allEvents.stream().map(o -> modelMapper.map(o, EventWithDeclarationDto.class)).toList();
            collect.forEach(o -> userEventRepository.findByUserIdAndEventId(user.get().getId(), o.getId())
                    .ifPresent(userEvent -> o.setUserDeclaration(userEvent.getDeclarationType().toString())));
            return collect;
        }


    }

    public Optional<EventWithDeclarationDto> getEventByIdWithDEclaration(Authentication authentication, Long id) {
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        Optional<EventWithDeclarationDto> event = eventRepository.findById(id).map(x -> modelMapper.map(x, EventWithDeclarationDto.class));
        if(authentication == null){
            return event;
        }else{
            if(event.isPresent()){
                userEventRepository.findByUserIdAndEventId(user.get().getId(), event.get().getId())
                    .ifPresent(userEvent -> event.get().setUserDeclaration(userEvent.getDeclarationType().toString()));
                return event;
            }

        }
        return event;
    }
}
