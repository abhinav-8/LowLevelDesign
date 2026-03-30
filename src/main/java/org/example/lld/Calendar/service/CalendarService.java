package org.example.lld.Calendar.service;

import org.example.lld.Calendar.model.*;
import org.example.lld.Calendar.repository.IEventRepository;
import org.example.lld.Calendar.repository.IUserRepostitory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalendarService {

    private final IEventRepository eventRepository;
    private final IUserRepostitory userRepository;

    public CalendarService(IEventRepository eventRepository, IUserRepostitory userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    public Event createEvent(String id, Slot slot, String title, List<Participant> participants, Location location, BusyStatus busyStatus) {
        boolean hasOrganizer = participants.stream().anyMatch(p -> p.getParticipantRole().equals(ParticipantRole.ORGANIZER));
        if(!hasOrganizer) {
            throw new IllegalArgumentException("You need to add an organizer to this event!");
        }
        Event event = new Event(id, slot, participants, title, location, busyStatus);
        return eventRepository.save(event);
    }

    public void respond(String eventId, String userId, RSVPStatus status) {
        Event event = eventRepository.findById(eventId);

        for (Participant p : event.getParticipants()) {
            if (p.getUser().getId().equals(userId)) {
                p.setResponseStatus(status);
            }
        }
    }

    public Event getEvent(String id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsForUser(String userId) {
        return eventRepository.getEventsByUser(userId);
    }

    public void updateEvent(Event updated) {
        eventRepository.save(updated);
    }

    public void deleteEvent(String id) {
        eventRepository.delete(id);
    }

    public List<Slot> findFreeSlots(List<String> userIds, int startHour, int startMin, int endHour, int endMin, long duration, long step) {
        long startTime = startHour * 60L + startMin;
        long endTime = endHour * 60L + endMin;

        //Find all the busy slots

        List<Slot> busySlots = new ArrayList<>();

        for(String userId : userIds) {
            busySlots.addAll(eventRepository.getEventsByUser(userId).stream()
                    .filter(e -> e.getBusyStatus() == BusyStatus.BUSY)
                    .filter(e -> e.getParticipants().stream().anyMatch(p -> p.getUser().getId().equals(userId) && p.getResponseStatus() != RSVPStatus.REJECTED))
                    .map(Event::getSlot)
                    .toList());
        }

        //Merge intervals
        busySlots.sort(Comparator.comparingLong(Slot::getStartTime));
        List<Slot> mergedSlots = new ArrayList<>();

        for(Slot slot : busySlots) {
            if(mergedSlots.isEmpty() || mergedSlots.get(mergedSlots.size() - 1).getEndTime() < slot.getStartTime()) {
                mergedSlots.add(slot);
            } else {
                mergedSlots.set(mergedSlots.size() - 1, new Slot(
                        mergedSlots.getLast().getStartTime(),
                        Math.max(mergedSlots.getLast().getEndTime(), slot.getEndTime())
                ));
            }
        }

        List<Slot> freeSlots = new ArrayList<>();
        long prevEnd = startTime;
        for(Slot slot : mergedSlots) {
            freeSlots.addAll(generateFreeSlots(prevEnd, slot.getStartTime(), duration, step));
            prevEnd = slot.getEndTime();
        }

        freeSlots.addAll(generateFreeSlots(prevEnd, endTime, duration, step));

        return freeSlots;
    }


    public List<Slot> generateFreeSlots(long startTime, long endTime, long duration, long step) {
        List<Slot> freeSlots = new ArrayList<>();
        long curr = startTime;
        while(curr + duration <= endTime) {
            freeSlots.add(new Slot(curr, curr + duration));
            curr = curr + step;
        }
        return freeSlots;

//        step = 15 minutes, duration = 30 minutes
//        11-11:30 , 11:15-11:45  11:30-12:00
    }
}
