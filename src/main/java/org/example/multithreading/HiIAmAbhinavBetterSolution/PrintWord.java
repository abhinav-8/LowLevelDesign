package org.example.multithreading.HiIAmAbhinavBetterSolution;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;

@AllArgsConstructor
public class PrintWord implements Runnable {

    private final State state;
    private final Turn currTurn;
    private final Turn nextTurn;

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Inside " + currTurn);
        synchronized (state) {
            while(!Objects.equals(state.nextWord, currTurn)) {
                System.out.println("Waiting for " + currTurn);
                state.wait();
                System.out.println("Woken up from " + currTurn);
            }
            System.out.print(currTurn + "---");
            state.nextWord = nextTurn;
            state.notifyAll();
        }
        System.out.println("Outside " + currTurn);
    }

}
