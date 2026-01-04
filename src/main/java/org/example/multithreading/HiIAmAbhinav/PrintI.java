package org.example.multithreading.HiIAmAbhinav;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;

@AllArgsConstructor
public class PrintI implements Runnable {
    final private State state;

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Inside I");
        synchronized(state) {
            while(!Objects.equals(state.word, "I")){
                System.out.println("Waiting for I");
                state.wait();
                System.out.println("Woken up I");
            }
            System.out.print("I ");
            state.word = "Am";
            state.notifyAll();
        }
        System.out.println("Outside I");
    }
}
