package Multithreading.ProducerConsumer;

public class Main {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();

        Thread producerThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                //
            }
            obj.addItem();
        }
        );

        Thread consumerThread = new Thread(() -> {obj.consumeItem();});

        producerThread.start();
        consumerThread.start();
    }
}
