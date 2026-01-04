package org.example.multithreading.BoundedBlockingQueue;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@AllArgsConstructor
public class BoundedBlockingQueue {

    private final List<Integer> items;
    private final int capacity;

    @SneakyThrows
    public void enqueue(int value) {
        synchronized (items) {  //Could have used this as well but this is the state on which everything depends
            while(items.size() >= capacity) {
                System.out.println("Waiting for qnueue of " + value);
                items.wait();
            }
            items.add(value);
            items.notifyAll();
            System.out.println("Enqueued " + value);
        }

    }

    @SneakyThrows
    public void dequeue() {
        synchronized (items) { //Could have used this as well but this is the state on which everything depends
            while(items.isEmpty()) {
                System.out.println("Waiting for deque of as queue is empty");
                items.wait();
            }

            int removedVal = items.removeFirst();
            items.notifyAll();
            System.out.println("Dequeued " + removedVal);
        }
    }
}
