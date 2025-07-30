package Multithreading.StampedOptimisticLock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    int a = 10;
    StampedLock lock = new StampedLock();

    public void produce() {
        long stamp = lock.tryOptimisticRead();
        try {
            System.out.println("Optimistic Read Lock acquired"); //though it's not really a lock
            a = 11 ;
            Thread.sleep(1000);
            if(lock.validate(stamp)) {
                System.out.println("Updated value successfully");
            } else {
                System.out.println("Update failed, rolling back");
                a = 10; //rollback
            }
        } catch (Exception e) {
            //
        }
    }

    public void consume() {
        long stamp = lock.writeLock();
        try {
            System.out.println("Write Lock acquired");
            a = 9;
        } catch (Exception e) {
            //
        } finally {
            System.out.println("Write Lock released");
            lock.unlockWrite(stamp);
        }
    }
}
