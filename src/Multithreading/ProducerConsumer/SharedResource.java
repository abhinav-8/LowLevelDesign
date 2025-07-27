package Multithreading.ProducerConsumer;

public class SharedResource {

    boolean isItemAvailable = false;

    public synchronized void addItem() {
        System.out.println("Adding new item");
        isItemAvailable = true;
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("Consuming new item");

        //Using while instead of if as recommended in oracle docs
        //Using while loop to avoid "spurious wake-up" due to system noise
        while(!isItemAvailable) {
            try {
                System.out.println("Waiting for new item");
                wait(); //Releases monitor lock
                System.out.println("Wait Released");
            } catch (Exception e) {
                //
            }
        }
        isItemAvailable = false;
    }
}
