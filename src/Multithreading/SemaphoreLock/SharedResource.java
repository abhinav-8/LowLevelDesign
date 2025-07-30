package Multithreading.SemaphoreLock;

import java.util.concurrent.Semaphore;

public class SharedResource {
    Semaphore lock = new Semaphore(2);
    boolean isAvailable = false;

    public void produce() {
        try {
            lock.acquire();
            System.out.println("Lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(2000);
        } catch (Exception e) {
            //
        } finally {
            System.out.println("Lock released by " + Thread.currentThread().getName());
            lock.release();
        }
    }
}
