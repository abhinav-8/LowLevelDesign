package Multithreading.ProducerConsumerWithQueue;

public class SharedResourceMain {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(3);

        Thread prod = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                sharedResource.produce(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                sharedResource.consume();
            }
        });

        prod.start();
        consumer.start();
    }
}
