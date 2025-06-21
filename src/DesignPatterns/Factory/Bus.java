package DesignPatterns.Factory;

public class Bus implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bus Started");
    }

    @Override
    public void stop() {
        System.out.println("Bus Stopped");
    }
}
