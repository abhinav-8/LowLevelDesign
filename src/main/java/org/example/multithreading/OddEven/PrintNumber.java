package org.example.multithreading.OddEven;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;


@AllArgsConstructor
public class PrintNumber implements Runnable {

    private int currNumber;
    private int maxNumber;
    private final State state;
    private final Turn currSeries;
    private final int inc;

    @Override
    @SneakyThrows
    public void run() {
        while(currNumber <= maxNumber) {
            synchronized (state) {
                while (state.getTurn() != currSeries) {
                    state.wait();
                }
                System.out.println(currNumber + "----"+ Thread.currentThread().getName());
                state.setTurn(state.getTurn().next());
                currNumber += inc;
                state.notifyAll();
            }
        }
    }
}
