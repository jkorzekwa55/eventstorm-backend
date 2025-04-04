package com.example.eventstormbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private String description;
    @ManyToMany
    private Set<Category> category = new HashSet<>();
    private String city;
    private String venueName;
    private String venueAddress;
    private String postalCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @OneToOne
    private User owner;
    @OneToMany(mappedBy = "event")
    private Set<UserEvent> userEvents = new HashSet<>();
    @OneToMany
    private Set<Image> images = new HashSet<>();

    void addImage(Image image) {
        images.add(image);
    }
}
