package org.example.multithreading.OddEvenZeroFibbonacci;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;


@AllArgsConstructor
public class PrintApSeries implements Runnable {

    private int currNumber;
    private int noOfElements;
    private final State state;
    private final Turn currSeries;
    private final int inc;

    @Override
    @SneakyThrows
    public void run() {
        int i = 1;
        while(i <= noOfElements) {
            synchronized (state) {
                while (state.getTurn() != currSeries) {
                    state.wait();
                }
                System.out.println(currSeries + ":" + currNumber);
                state.setTurn(state.getTurn().next());
                i++;
                currNumber += inc;
                state.notifyAll();
            }
        }
    }
}
