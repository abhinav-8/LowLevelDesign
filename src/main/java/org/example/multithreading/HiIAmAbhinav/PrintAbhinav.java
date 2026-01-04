package org.example.multithreading.HiIAmAbhinav;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;

@AllArgsConstructor
public class PrintAbhinav implements Runnable {
    private final State state;

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Inside Abhinav");
        synchronized (state) {
            while(!Objects.equals(state.word, "Abhinav")){
                System.out.println("Waiting for Abhinav");
                state.wait();
                System.out.println("Woken up Abhinav");
            }
            System.out.print("Abhinav ");
            state.word = null;
            state.notifyAll();
        }
        System.out.println("Outside Abhinav");
    }
}
