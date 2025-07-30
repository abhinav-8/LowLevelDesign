package Multithreading.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread t1 = new Thread(() -> {
            sharedResource.produce(lock);
        });

        Thread t2 = new Thread(() -> {
            sharedResource.produce(lock);
        });

        SharedResource sharedResource1 = new SharedResource();
        Thread t3 = new Thread(() -> {
            sharedResource1.consume(lock);
        });

        Thread t4 = new Thread(() -> {
            sharedResource1.consume(lock);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
