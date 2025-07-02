package DesignPatterns.State;

public class YellowState implements TrafficLightState{

    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Switching state from YELLOW to RED");
        context.setState(new RedState());
    }

    @Override
    public String getColor() {
        return "YELLOW";
    }
}
