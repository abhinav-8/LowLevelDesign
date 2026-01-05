package org.example.multithreading.Sqs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Consumer {

    @Getter
    private final String name;

    @Getter
    private boolean isFree = true; //No use as such

    @SneakyThrows
    public void consume(ICalculationMessage message) {
        isFree = false;
        Thread.sleep(5000);
        System.out.println("Message consumed and processed: " + (message.a + message.b));
        isFree = true;
    }

}
