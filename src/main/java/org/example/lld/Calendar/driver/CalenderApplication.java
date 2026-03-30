package org.example.lld.Calendar.driver;

import lombok.SneakyThrows;
import org.example.lld.Calendar.model.*;
import org.example.lld.Calendar.repository.IEventRepository;
import org.example.lld.Calendar.repository.IUserRepostitory;
import org.example.lld.Calendar.repository.InMemoryEventRepository;
import org.example.lld.Calendar.repository.InMemoryUserRepository;
import org.example.lld.Calendar.service.CalendarService;

import java.util.List;
import java.util.UUID;

public class CalenderApplication {
    @SneakyThrows
    static void main(String[] args) {
        IEventRepository eventRepo = new InMemoryEventRepository();
        IUserRepostitory userRepostitory = new InMemoryUserRepository();
        CalendarService service = new CalendarService(eventRepo, userRepostitory);

        service.addUser(new User("1", "Abhinav", "abhinav@gmail"));
        service.addUser(new User("2", "Avinash", "avi@gmail"));
        service.addUser(new User("3", "Abhishek", "abhishek@gmail"));
        service.addUser(new User("4", "Sounak", "sounak@gmail"));
        service.addUser(new User("5", "Kartike", "kartike@gmail"));

        service.createEvent(
                UUID.randomUUID().toString(),
                new Slot(10, 0, 13, 0),
                "Meeting for Appraisal Discussion",
                List.of(new Participant(userRepostitory.getUser("1"), ParticipantRole.ORGANIZER),
                        new Participant(userRepostitory.getUser("2"), ParticipantRole.REQUIRED),
                        new Participant(userRepostitory.getUser("3"), ParticipantRole.OPTIONAL)),
                new Location("4", "Zoom Meeting", new VirtualLocationTypeData("zoom/123", "zoom")),
                BusyStatus.BUSY
        );

        Event event2 = service.createEvent(
                UUID.randomUUID().toString(),
                new Slot(11, 0,15, 0),
                "Meeting for Appraisal Discussion",
                List.of(new Participant(userRepostitory.getUser("1"), ParticipantRole.ORGANIZER),
                        new Participant(userRepostitory.getUser("4"), ParticipantRole.OPTIONAL),
                        new Participant(userRepostitory.getUser("5"), ParticipantRole.EDITOR)),
                new Location(UUID.randomUUID().toString(), "Zoom Meeting", new VirtualLocationTypeData("zoom/123", "zoom")),
                BusyStatus.BUSY
        );

        service.createEvent(
                UUID.randomUUID().toString(),
                new Slot(10, 0,12, 0),
                "Meeting for Appraisal Discussion",
                List.of(new Participant(userRepostitory.getUser("4"), ParticipantRole.ORGANIZER),
                        new Participant(userRepostitory.getUser("5"), ParticipantRole.OPTIONAL),
                        new Participant(userRepostitory.getUser("2"), ParticipantRole.EDITOR)),
                new Location(UUID.randomUUID().toString(), "Zoom Meeting", new VirtualLocationTypeData("zoom/123", "zoom")),
                BusyStatus.BUSY
        );

        service.respond(event2.getId(), "1", RSVPStatus.REJECTED);

        Thread.sleep(1000);
        System.out.println("Free slots:");
        System.out.println(service.findFreeSlots(List.of("1", "2", "3"), 9, 0, 16, 30, 30, 15).toString());
        Thread.sleep(1000);

        service.deleteEvent(event2.getId());
        Thread.sleep(1000);
        System.out.println("Free slots2:");
        System.out.println(service.findFreeSlots(List.of("1", "2", "3"), 9, 0, 16, 30, 30, 15).toString());
    }
}
