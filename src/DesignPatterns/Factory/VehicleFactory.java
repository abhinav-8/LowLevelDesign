package DesignPatterns.Factory;

public class VehicleFactory {
    public static Vehicle getVehicle(String vehicleType){
        Vehicle vehicle = null;
        switch(vehicleType){
            case "Bus":
                return new Bus();
            case "Truck":
                return new Truck();
            default:
                return null;
        }
    }
}
