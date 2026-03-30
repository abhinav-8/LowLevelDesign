## Problem Statement:
Design a calendar Application (similar like Google Calendar)

- Ability to create, update, delete an Event
- Get Calendar for a user Ui
- Get Event details.
- For a given set of users[U1, U2,....Un] identity a common free slot of time.
- An event once created, can be either accepted or rejected by the constituent users - if neither it should be in neutral state.

- An event would typically consist of {start, end, location, Owner, user-list, title}.
- Events can either be like meetings(with a dedicated location and appropriate guest-list) or as well be like holidays, birthdays, reminders etc.


# Code:
---

# 🧱 1. Core Models

---

## ✅ Event

```java
@Getter
@AllArgsConstructor
public class Event {
    private String id;
    private Slot slot;
    private List<Participant> participants;
    private String title;
    private Location location;
    private BusyStatus busyStatus;
}
```

👉 Event = time + participants + location + busy/free

---

## ✅ Participant

```java
@Getter
@Setter
public class Participant {
    private User user;
    private ParticipantRole participantRole;
    private RSVPStatus responseStatus = RSVPStatus.PENDING;

    public Participant(User user, ParticipantRole role) {
        this.user = user;
        this.participantRole = role;
    }
}
```

👉 Tracks:

* Role (organizer, optional…)
* Response (accepted/rejected)

---

## ✅ Slot (Time)

```java
@Getter
@AllArgsConstructor
public class Slot {
    private long startTime;
    private long endTime;
}
```

👉 Always use **minutes**

```java
long time = hour * 60 + min;
```

---

## ✅ Location (Composition)

```java
@AllArgsConstructor
public class Location {
    private String id;
    private String title;
    private ILocationTypeData typeData;
}
```

```java
public interface ILocationTypeData {
    String getLocationDetails();
    LocationType getLocationType();
}
```

---

### Example Type

```java
@AllArgsConstructor
@Getter
public class VirtualLocationTypeData implements ILocationTypeData {
    private String meetingUrl;
    private String platform;

    public String getLocationDetails() {
        return "Meeting platform: " + platform + " URL: " + meetingUrl;
    }

    public LocationType getLocationType() {
        return LocationType.VIRTUAL;
    }
}
```

---

# 🗄️ 2. Repository Layer

---

## Interface

```java
public interface IEventRepository {
    Event save(Event event);
    Event findById(String id);
    List<Event> getEventsByUser(String userId);
    void delete(String eventId);
}
```

---

## InMemoryEventRepository

### Data Structures

```java
Map<String, Event> events = new HashMap<>();
Map<String, List<Event>> userEvents = new HashMap<>();
```

👉 `userEvents` = index (fast lookup)

---

## Save

```java
@Override
public Event save(Event event) {
    Event oldEvent = events.get(event.getId());

    if (oldEvent != null) {
        delete(oldEvent.getId());
    }

    events.put(event.getId(), event);

    for (Participant p : event.getParticipants()) {
        userEvents
            .computeIfAbsent(p.getUser().getId(), k -> new ArrayList<>())
            .add(event);
    }

    return event;
}
```

---

## Delete

```java
@Override
public void delete(String eventId) {
    Event event = events.get(eventId);
    if (event == null) return;

    events.remove(eventId);

    for (Participant p : event.getParticipants()) {
        List<Event> list = userEvents.get(p.getUser().getId());

        if (list != null) {
            list.remove(event);

            if (list.isEmpty()) {
                userEvents.remove(p.getUser().getId());
            }
        }
    }
}
```

---

# ⚙️ 3. CalendarService

---

## Create Event

```java
public Event createEvent(String id, Slot slot, String title,
                         List<Participant> participants,
                         Location location,
                         BusyStatus busyStatus) {

    boolean hasOrganizer = participants.stream()
        .anyMatch(p -> p.getParticipantRole() == ParticipantRole.ORGANIZER);

    if (!hasOrganizer) {
        throw new IllegalArgumentException("Organizer required");
    }

    Event event = new Event(id, slot, participants, title, location, busyStatus);
    return eventRepository.save(event);
}
```

---

## Respond to Event

```java
public void respond(String eventId, String userId, RSVPStatus status) {
    Event event = eventRepository.findById(eventId);

    for (Participant p : event.getParticipants()) {
        if (p.getUser().getId().equals(userId)) {
            p.setResponseStatus(status);
        }
    }
}
```

---

# 🔥 4. Free Slot Algorithm (Core)

---

## Step 1: Collect Busy Slots

```java
List<Slot> busySlots = new ArrayList<>();

for (String userId : userIds) {
    busySlots.addAll(
        eventRepository.getEventsByUser(userId).stream()
            .filter(e -> e.getBusyStatus() == BusyStatus.BUSY)
            .filter(e -> e.getParticipants().stream()
                .anyMatch(p ->
                    p.getUser().getId().equals(userId) &&
                    p.getResponseStatus() != RSVPStatus.REJECTED
                )
            )
            .map(Event::getSlot)
            .toList()
    );
}
```

👉 `anyMatch` ensures:

> user is part of event AND hasn’t rejected

---

## Step 2: Sort

```java
busySlots.sort(Comparator.comparingLong(Slot::getStartTime));
```

---

## Step 3: Merge Intervals

```java
List<Slot> merged = new ArrayList<>();

for (Slot s : busySlots) {
    if (merged.isEmpty() ||
        merged.get(merged.size() - 1).getEndTime() < s.getStartTime()) {
        merged.add(s);
    } else {
        Slot last = merged.get(merged.size() - 1);

        merged.set(merged.size() - 1,
            new Slot(
                last.getStartTime(),
                Math.max(last.getEndTime(), s.getEndTime())
            )
        );
    }
}
```

---

## Step 4: Find Free Windows

```java
List<Slot> freeSlots = new ArrayList<>();
long prevEnd = startTime;

for (Slot s : merged) {
    freeSlots.addAll(generateFreeSlots(prevEnd, s.getStartTime(), duration, step));
    prevEnd = s.getEndTime();
}

freeSlots.addAll(generateFreeSlots(prevEnd, endTime, duration, step));
```

---

## Step 5: Generate Slots (Sliding Window)

```java
public List<Slot> generateFreeSlots(long start, long end,
                                    long duration, long step) {

    List<Slot> res = new ArrayList<>();
    long curr = start;

    while (curr + duration <= end) {
        res.add(new Slot(curr, curr + duration));
        curr += step;
    }

    return res;
}
```

---

### Example

```text
Free window: 11:00–12:15
duration = 30
step = 15
```

```text
11:00–11:30
11:15–11:45
11:30–12:00
11:45–12:15
```

---