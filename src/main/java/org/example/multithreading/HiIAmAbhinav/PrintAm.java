package org.example.multithreading.HiIAmAbhinav;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;

@AllArgsConstructor
public class PrintAm implements Runnable {
    private final State state;

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Inside Am");
        synchronized(state) {
            while(!Objects.equals(state.word, "Am")){
                System.out.println("Waiting for Am");
                state.wait();
                System.out.println("Woken up Am");
            }
            System.out.print("Am ");
            state.word = "Abhinav";
            state.notifyAll();
        }
        System.out.println("Outside Am");
    }
}
