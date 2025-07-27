package DesignPatterns.State;

public interface TrafficLightState {
    void next(TrafficLightContext context);
    String getColor();
}
