package org.example.multithreading.Sqs;

public class Main {
    public static void main(String[] args) {
        SqsQueue queue = new SqsQueue();
        queue.registerConsumer(new Consumer("12345"));
        queue.registerConsumer(new Consumer("5678"));

        queue.publishMessage(new ICalculationMessage(1,1));
        queue.publishMessage(new ICalculationMessage(1,2));

        queue.publishMessage(new ICalculationMessage(10,10));
        queue.publishMessage(new ICalculationMessage(10,20));
    }
}
