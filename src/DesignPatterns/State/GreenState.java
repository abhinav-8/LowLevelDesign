package DesignPatterns.State;

public class GreenState implements  TrafficLightState{

    @Override
    public String getColor() {
        return "GREEN";
    }

    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Switching state from GREEN to YELLOW");
        context.setState(new YellowState());
    }
}
