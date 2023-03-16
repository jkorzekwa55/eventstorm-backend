package com.example.eventstormbackend.dto;

import com.example.eventstormbackend.entity.Category;
import com.example.eventstormbackend.entity.Image;
import com.example.eventstormbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {
    private Long id;
    private String name;
    private Set<Category> category = new HashSet<>();
    private String city;
    private String venueName;
    private String venueAddress;
    private String postalCode;
    private LocalDateTime startDateTime;
    private User owner;
    private Set<Image> images = new HashSet<>();
}
