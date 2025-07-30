package Multithreading.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean isAvailable = true;
    public void produce() {
        try {
            lock.lock();
            System.out.println("System lock acquired by " + Thread.currentThread().getName());
            while (isAvailable) {
                //It's already avaialble , so producer should wait
                System.out.println("Producer thread is waiting: " + Thread.currentThread().getName());
                condition.await();
            }
            isAvailable = true;
            condition.signalAll();
        } catch (Exception e) {
            //
        } finally {
            System.out.println("System lock released by " + Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void consume() {
        try {
            Thread.sleep(1000);
            lock.lock();
            while(!isAvailable){
                //Already not available , consumer has to wait
                System.out.println("Consumer thread is waiting: " + Thread.currentThread().getName());
                condition.await();
            }
            isAvailable = false;
            condition.signal();
        } catch (Exception e) {
            //
        } finally {
            System.out.println("System lock released by " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
