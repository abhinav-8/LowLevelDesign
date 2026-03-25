package org.example.lld.Elevator.strategy;

import org.example.lld.Elevator.model.Elevator;

public interface SchedulingStrategy {
    Integer getNextStop(Elevator elevator);
}
