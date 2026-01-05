package org.example.multithreading.Sqs;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SqsQueue {

    private final List<Consumer> consumerList;
    private final Queue<ICalculationMessage> queue;

    public SqsQueue() {
        queue = new LinkedList<>();
        consumerList = new ArrayList<>();
    }

    public void registerConsumer(Consumer consumer) {
        System.out.println("Register consumer " + consumer.getName());
        consumerList.add(consumer);
        new Thread(new ConsumerWorker(queue, consumer)).start();
    }

    public void publishMessage(ICalculationMessage  message) {
        synchronized (queue) {
            queue.add(message);
            queue.notifyAll();
        }
    }
}
