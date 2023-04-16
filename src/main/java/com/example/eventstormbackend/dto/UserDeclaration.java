package com.example.eventstormbackend.dto;

import com.example.eventstormbackend.model.DeclarationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDeclaration {
    private Long userId;
    private Long eventId;
    private DeclarationType declarationType;
}
