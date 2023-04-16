package com.example.eventstormbackend.service;

import com.example.eventstormbackend.dto.UserDeclaration;
import com.example.eventstormbackend.entity.User;
import com.example.eventstormbackend.entity.UserEvent;
import com.example.eventstormbackend.repository.UserEventRepository;
import com.example.eventstormbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserEventService {
    private final UserEventRepository userEventRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<UserEvent> getAllUserEventDeclarations(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return modelMapper.map(userEventRepository.findAllByUserId(user.getId()), new TypeToken<List<UserDeclaration>>() {
        }.getType());
    }
}
