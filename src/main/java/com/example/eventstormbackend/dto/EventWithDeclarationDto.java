package com.example.eventstormbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventWithDeclarationDto {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private String ownerName;
    private String city;
    private String venueName;
    private String venueAdress;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userDeclaration;
}
