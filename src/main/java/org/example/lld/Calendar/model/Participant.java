package org.example.lld.Calendar.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participant {
    private User user;
    private ParticipantRole participantRole;
    public Participant(User user, ParticipantRole participantRole) {
        this.user = user;
        this.participantRole = participantRole;
    }
    private RSVPStatus responseStatus = RSVPStatus.PENDING;
}
