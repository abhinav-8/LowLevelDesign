package DesignPatterns.State;

public interface TrafficLightState {
    public void next(TrafficLightContext context);
    String getColor();
}
