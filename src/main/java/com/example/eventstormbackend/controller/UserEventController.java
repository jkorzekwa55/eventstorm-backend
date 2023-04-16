package com.example.eventstormbackend.controller;

import com.example.eventstormbackend.entity.UserEvent;
import com.example.eventstormbackend.service.UserEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("declaration")
public class UserEventController {
    private final UserEventService userEventService;

    @GetMapping
    public List<UserEvent> getAllDeclarations(Authentication authentication) {
        return userEventService.getAllUserEventDeclarations(authentication);
    }
}
