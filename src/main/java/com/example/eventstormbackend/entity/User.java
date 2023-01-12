package com.example.eventstormbackend.entity;

import com.example.eventstormbackend.model.Role;
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
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean official;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @OneToOne
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user")
    private Set<UserEvent> userEvents = new HashSet<>();
}
