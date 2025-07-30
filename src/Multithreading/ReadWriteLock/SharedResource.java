package Multithreading.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {

    boolean isAvailable = false;

    public void produce(ReadWriteLock lock) {
        try{
            lock.readLock().lock();
            System.out.println("Read Lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(8000);
        } catch (Exception e) {
            //
        } finally {
            System.out.println("Read Lock Release by: " + Thread.currentThread().getName());
            lock.readLock().unlock();
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Write Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
        } catch (Exception e) {
            //
        } finally {
            System.out.println("Write lock released by: " + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }
}
