package org.example.multithreading.BoundedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Runner r = new Runner(2);
        r.run();
    }
}
