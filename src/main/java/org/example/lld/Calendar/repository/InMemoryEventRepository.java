package org.example.lld.Calendar.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.lld.Calendar.model.Event;
import org.example.lld.Calendar.model.Participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class InMemoryEventRepository implements IEventRepository{

    private Map<String, List<Event>> userEvents = new HashMap<>();
    private Map<String, Event> events = new HashMap<>();

    @Override
    public Event save(Event event) {
        Event oldEvent = events.get(event.getId());
        if(oldEvent != null) {
            delete(oldEvent.getId());
        }
        events.put(event.getId(), event);
        for(Participant participant : event.getParticipants()) {
            userEvents.computeIfAbsent(participant.getUser().getId(),k -> new ArrayList<>()).add(event);
        }
        System.out.println("Event saved successfully!");
        return event;
    }

    @Override
    public Event findById(String id) {
        return events.get(id);
    }

    @Override
    public List<Event> getEventsByUser(String userId) {
        return userEvents.getOrDefault(userId, List.of());
    }

    @Override
    public void delete(String eventId) {
        Event event = events.get(eventId);
        if(event == null) {
            System.out.println("Event with id " + eventId + " not found!");
            return;
        }
        events.remove(eventId);

        for(Participant participant : event.getParticipants()) {
            List<Event> list = userEvents.get(participant.getUser().getId());
            if (list != null) {
                list.remove(event);

                if (list.isEmpty()) {
                    userEvents.remove(participant.getUser().getId());
                }
            }
        }

        System.out.println("Even deleted successfully!");
    }

}
