package com.example.eventstormbackend.repository;

import com.example.eventstormbackend.entity.UserEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserEventRepository extends CrudRepository<UserEvent, Long> {
    List<UserEvent> findAllByUserId(Long userId);
}
