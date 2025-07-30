package Multithreading.SemaphoreLock;

public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(() -> {
            resource.produce();
        });

        Thread t2 = new Thread(() -> {
            resource.produce();
        });

        Thread t3 = new Thread(() -> {
            resource.produce();
        });

        Thread t4 = new Thread(() -> {
            resource.produce();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
