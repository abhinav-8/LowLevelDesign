package Multithreading.StampedReadWriteLock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {

    StampedLock lock = new StampedLock();
    boolean isAvailable = false;

    public void producer () {
        long stamp = lock.readLock();
        try {
            System.out.println("System read lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(2000);
        } catch (Exception e) {
            //
        } finally {
            System.out.println("System read lock released by: " + Thread.currentThread().getName());
            lock.unlockRead(stamp);
        }
    }

    public void consume () {
        long stamp = lock.writeLock();
        try {
            System.out.println("System write lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(2000);
        } catch (Exception e) {
            //
        } finally {
            System.out.println("System write lock released by: " + Thread.currentThread().getName());
            lock.unlockWrite(stamp);
        }
    }
}
