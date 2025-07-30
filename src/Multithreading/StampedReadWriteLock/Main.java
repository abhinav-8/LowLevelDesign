package Multithreading.StampedReadWriteLock;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(() -> {
            sharedResource.producer();
        });

        Thread t2 = new Thread(() -> {
            sharedResource.producer();
        });


        Thread t3 = new Thread(() -> {
            sharedResource.consume();
        });

        Thread t4 = new Thread(() -> {
            sharedResource.consume();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
