package com.example.eventstormbackend.repository;

import com.example.eventstormbackend.entity.UserEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserEventRepository extends CrudRepository<UserEvent, Long> {
    List<UserEvent> findAllByUserId(Long userId);

    Optional<UserEvent> findByUserIdAndEventId(Long userId, Long eventId);
}
