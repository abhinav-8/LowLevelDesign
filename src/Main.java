import DesignPatterns.Builder.Car;
import DesignPatterns.Factory.Vehicle;
import DesignPatterns.Factory.VehicleFactory;
import DesignPatterns.Singleton.Logger;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");


        //Call factory
        Vehicle vehicle = VehicleFactory.getVehicle("Bus");
        if (vehicle != null) {
            vehicle.start();
            sleep(2000);
            vehicle.stop();
        }

        //Call singleton
        Logger logger = Logger.getLogger();
        logger.log("siiiiiiiiiu");

        //Call builder
        Car.CarBuilder carBuilder = new Car.CarBuilder();

        Car obj = carBuilder.
                setEngine("testEngine12").
                setColor("Red").
                setWheels(6).
                build();
        System.out.println(obj.toString());
    }
}