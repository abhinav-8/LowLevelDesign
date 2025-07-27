package DesignPatterns.State;

public class Main {
    public static void main(String[] args) {
        TrafficLightContext trafficLightContext = new TrafficLightContext();
        trafficLightContext.next();
        trafficLightContext.next();
        trafficLightContext.next();
        trafficLightContext.next();
        trafficLightContext.next();
    }
}
