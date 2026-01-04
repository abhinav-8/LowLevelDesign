package org.example.multithreading.OddEven;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import static org.example.multithreading.OddEven.Turn.EVEN;
import static org.example.multithreading.OddEven.Turn.ODD;

@AllArgsConstructor
public class Runner implements Runnable {
    private final int n;
    @Override
    @SneakyThrows
    public void run() {
        State state = new State(EVEN);
        PrintNumber oddSeries = new PrintNumber(1, n, state, ODD, 2);
        PrintNumber evenSeries = new PrintNumber(0, n, state, EVEN, 2);
        new Thread(oddSeries).start();
        new Thread(evenSeries).start();
    }
}
