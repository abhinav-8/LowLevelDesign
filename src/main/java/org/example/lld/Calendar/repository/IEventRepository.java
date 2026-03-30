package org.example.lld.Calendar.repository;

import org.example.lld.Calendar.model.Event;

import java.util.List;

public interface IEventRepository {
    Event save(Event event);
    Event findById(String id);
    List<Event> getEventsByUser(String userId);
    void delete(String eventId);
}
