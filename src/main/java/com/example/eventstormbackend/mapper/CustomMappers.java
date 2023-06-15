package com.example.eventstormbackend.mapper;

import com.example.eventstormbackend.dto.EventWithDeclarationDto;
import com.example.eventstormbackend.dto.UserDeclaration;
import com.example.eventstormbackend.entity.Event;
import com.example.eventstormbackend.entity.UserEvent;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class CustomMappers {
    private final ModelMapper modelMapper;

    public CustomMappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        setUserEventDtoMapper();
        setEventDeclarationDtoMapper();
    }

    private void setUserEventDtoMapper() {
        TypeMap<UserEvent, UserDeclaration> typeMap = modelMapper.createTypeMap(UserEvent.class, UserDeclaration.class);
        typeMap.addMappings(m -> m.map(src -> src.getUser().getId(), UserDeclaration::setUserId));
        typeMap.addMappings(m -> m.map(src -> src.getEvent().getId(), UserDeclaration::setEventId));
        typeMap.addMapping(UserEvent::getDeclarationType, UserDeclaration::setDeclarationType);
    }

    private void setEventDeclarationDtoMapper() {
        TypeMap<Event, EventWithDeclarationDto> typeMap = modelMapper.createTypeMap(Event.class, EventWithDeclarationDto.class);
        typeMap.addMapping(Event::getId, EventWithDeclarationDto::setId);
        typeMap.addMapping(Event::getName, EventWithDeclarationDto::setName);
        typeMap.addMapping(Event::getDescription, EventWithDeclarationDto::setDescription);
        typeMap.addMappings(m -> m.map(src -> src.getOwner().getId(), EventWithDeclarationDto::setOwnerId));
        typeMap.addMappings(m -> m.map(src -> src.getOwner().getUsername(), EventWithDeclarationDto::setOwnerName));
        typeMap.addMapping(Event::getCity, EventWithDeclarationDto::setCity);
        typeMap.addMapping(Event::getVenueName, EventWithDeclarationDto::setVenueName);
        typeMap.addMapping(Event::getStartDateTime, EventWithDeclarationDto::setStartDate);
        typeMap.addMapping(Event::getVenueAddress, EventWithDeclarationDto::setVenueAdress);
        typeMap.addMapping(Event::getStartDateTime, EventWithDeclarationDto::setStartDate);
    }
}
