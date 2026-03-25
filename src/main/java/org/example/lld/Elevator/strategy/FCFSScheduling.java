package org.example.lld.Elevator.strategy;

import org.example.lld.Elevator.model.Elevator;

import java.util.TreeSet;

public class FCFSScheduling implements SchedulingStrategy {
    @Override
    public Integer getNextStop(Elevator elevator) {
        TreeSet<Integer> requests = elevator.getRequests();
        if(requests.isEmpty()) return null;
        return requests.getFirst();
    }
}
