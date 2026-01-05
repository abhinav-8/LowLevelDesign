package org.example.multithreading.Singleton;

public class ThreadSafeSingleton {

    //If everything would have been in synchronized, this wouldn't have been required
    //volatile ensures that reads and writes of that variable go to main memory so all
    // threads see the latest value instead of cpu cache
    private static volatile ThreadSafeSingleton instance;

    //Private Constructor to avoid instantiation directly
    private ThreadSafeSingleton() {
        System.out.println("ThreadSafeSingleton constructor " + Thread.currentThread().getName());
    }

    //Static as we need to call the function without any object being created
    public static ThreadSafeSingleton getInstance() {
        if(instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if(instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

}
