package org.example.multithreading.OddEvenZeroFibbonacci;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;


@AllArgsConstructor
public class PrintFibonacciSeries implements Runnable {

    private final int noOfElements;
    private final State state;
    private final Turn currSeries;

    @Override
    @SneakyThrows
    public void run() {
        int i = 1;
        int prevNumber = 0;
        int currNumber = 1;
        while(i <= noOfElements) {
            synchronized (state) {
                while (state.getTurn() != currSeries) {
                    state.wait();
                }
                System.out.println(currSeries + ":" + currNumber);
                state.setTurn(state.getTurn().next());
                int temp = currNumber;
                currNumber = currNumber + prevNumber;
                prevNumber = temp;
                i++;
                state.notifyAll();
            }
        }
    }
}
