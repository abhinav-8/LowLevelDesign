package Multithreading.ProducerConsumerWithQueue;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int element) {
        try{
            //If buffer is full, wait for the consumer to consume items
            while (sharedBuffer.size() == bufferSize) {
                System.out.println("Buffer is full, Producer is waiting for consumer");
                wait();
            }
            sharedBuffer.add(element);
            System.out.println("Producer produced " + element);
            //Notify the consumer that there are items to consume now
            notify();
        } catch (Exception e) {
            //
        }
    }

    public synchronized int consume() {
        try{
            //If buffer is empty, wait for producer to produce items
            while(sharedBuffer.size() == 0) {
                System.out.println("Buffer is empty, Consumer is waiting for producer");
                wait();
            }
            int element = sharedBuffer.poll();
            System.out.println("Consumer consumed " + element);
            //Notify the producer that there is space in the buffer now
            notify();
            return element;
        } catch (Exception e) {
            //
        }
        return 0;
    }
}
