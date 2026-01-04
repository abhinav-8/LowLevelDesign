package org.example.multithreading.HiIAmAbhinav;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;

@AllArgsConstructor
public class PrintHi implements Runnable {

    private final State state;

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Inside Hi");
        synchronized(state) {
            while(!Objects.equals(state.word, "Hi")){
                System.out.println("Waiting for Hi");
                state.wait();
                System.out.println("Woken up Hi");
            }
            System.out.print("Hi ");
            state.word = "I";
            state.notifyAll();
        }
        System.out.println("Outside Hi");
    }
}
