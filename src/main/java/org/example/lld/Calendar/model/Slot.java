package org.example.lld.Calendar.model;

import lombok.Getter;

@Getter
public class Slot {
    private final long startTime;
    private final long endTime;

    public Slot(int startHour, int startMin, int endHour, int endMin) {
        startTime = startHour * 60L + startMin;
        endTime = endHour * 60L + endMin;
    }

    public Slot(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Slot startTime = " + getTime(startTime) + " endTime = " + getTime(endTime) ;
    }
    private String getTime(long time) {
        return time/60 + ":" + time%60;
    }
}
