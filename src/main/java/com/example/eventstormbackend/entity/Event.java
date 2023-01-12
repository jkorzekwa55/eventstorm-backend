package com.example.eventstormbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Category> category = new HashSet<>();
    private String city;
    private String venueName;
    private String venueAddress;
    @OneToOne
    private User owner;
    @OneToMany(mappedBy = "event")
    private Set<UserEvent> userEvents = new HashSet<>();
}
