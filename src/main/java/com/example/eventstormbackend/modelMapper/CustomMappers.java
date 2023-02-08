package com.example.eventstormbackend.modelMapper;

import com.example.eventstormbackend.entity.Event;
import com.example.eventstormbackend.entity.ticketmaster.EventTicketMaster;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class CustomMappers {
    private final ModelMapper modelMapper;

    public CustomMappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        setEventTicketmasterMapper();
    }

    public void setEventTicketmasterMapper() {
        TypeMap<EventTicketMaster, Event> typeMap = modelMapper.createTypeMap(EventTicketMaster.class, Event.class);
        typeMap.addMapping(EventTicketMaster::getId, Event::setId);
    }
}
