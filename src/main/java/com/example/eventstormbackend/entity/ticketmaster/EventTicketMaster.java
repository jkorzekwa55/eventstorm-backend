package com.example.eventstormbackend.entity.ticketmaster;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventTicketMaster {
    @Id
    private String id;
    private String name;

}
