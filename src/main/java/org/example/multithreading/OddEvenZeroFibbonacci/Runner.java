package org.example.multithreading.OddEvenZeroFibbonacci;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import static org.example.multithreading.OddEvenZeroFibbonacci.Turn.*;

public class Runner implements Runnable {

    @Override
    @SneakyThrows
    public void run() {
        State state = new State(EVEN);
        PrintApSeries oddSeries = new PrintApSeries(1, 5, state, ODD, 2);
        PrintApSeries evenSeries = new PrintApSeries(2, 5, state, EVEN, 2);
        PrintApSeries zeroSeries = new PrintApSeries(0, 5, state, ZERO, 0);
        PrintFibonacciSeries fibonacciSeries = new PrintFibonacciSeries(5, state, FIBONACCI);
        new Thread(oddSeries).start();
        new Thread(evenSeries).start();
        new Thread(zeroSeries).start();
        new Thread(fibonacciSeries).start();
    }
}
