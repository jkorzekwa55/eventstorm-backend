package com.example.eventstormbackend.repository;

import com.example.eventstormbackend.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("SELECT s from Event s")
    List<Event> findAllEvents();
}
