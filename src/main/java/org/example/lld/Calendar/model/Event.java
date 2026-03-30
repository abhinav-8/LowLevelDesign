package org.example.lld.Calendar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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
