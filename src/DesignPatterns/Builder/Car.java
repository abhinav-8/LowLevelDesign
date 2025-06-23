package DesignPatterns.Builder;

public class Car {
    private Integer wheels;
    private Boolean sunroof;
    private String color;
    private String engine;

    private Car(CarBuilder builder) {
        this.wheels = builder.wheels;
        this.sunroof = builder.sunroof;
        this.color = builder.color;
        this.engine = builder.engine;
    }

    @Override
    public String toString() {
        return "Wheels: " + wheels + ", Sunroof: " + sunroof + ", Color: " + color + ", Engine: " + engine;
    }

    public static class CarBuilder {
        private Integer wheels = 4;
        private Boolean sunroof = false;
        private String color = "black";
        private String engine = null;

        public CarBuilder setWheels(Integer wheels) {
            this.wheels = wheels;
            return this;
        }
        public CarBuilder setSunroof(Boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }
        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }
        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
