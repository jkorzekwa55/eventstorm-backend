package com.example.eventstormbackend.mapper;

import com.example.eventstormbackend.dto.UserDeclaration;
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
    }

    private void setUserEventDtoMapper() {
        TypeMap<UserEvent, UserDeclaration> typeMap = modelMapper.createTypeMap(UserEvent.class, UserDeclaration.class);
        typeMap.addMappings(m -> m.map(src -> src.getUser().getId(), UserDeclaration::setUserId));
        typeMap.addMappings(m -> m.map(src -> src.getEvent().getId(), UserDeclaration::setEventId));
        typeMap.addMapping(UserEvent::getDeclarationType, UserDeclaration::setDeclarationType);
    }
}
