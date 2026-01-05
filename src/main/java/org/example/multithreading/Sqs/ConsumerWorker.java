package org.example.multithreading.Sqs;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Queue;

@AllArgsConstructor
public class ConsumerWorker implements Runnable {

    private final Queue<ICalculationMessage>  queue;
    private final Consumer consumer;

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            final ICalculationMessage msg;
            synchronized (queue) {
                while (queue.isEmpty()) {
                    queue.wait();
                }
                msg = queue.poll();
            }
            consumer.consume(msg);
        }
    }
}
