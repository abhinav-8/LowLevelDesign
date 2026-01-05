package org.example.multithreading.Singleton;

import lombok.SneakyThrows;

public class Runner implements Runnable {

    @Override
    @SneakyThrows
    public void run() {

        for (int i = 0 ; i < 10; i++) {
            new Thread(() -> {
                ThreadSafeSingleton threadSafeSingleton = ThreadSafeSingleton.getInstance();
            }).start();
        }
    }
}
