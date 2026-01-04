package org.example.multithreading.HiIAmAbhinav;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Runner implements Runnable{

    final State state = new State("Hi");

    @Override
    @SneakyThrows
    public void run() {
        Thread thread1 = new Thread(new PrintHi(state));
        thread1.start();
        Thread thread4 = new Thread(new PrintAbhinav(state));
        thread4.start();
        Thread thread3 = new Thread(new PrintAm(state));
        thread3.start();
        Thread thread2 = new Thread(new PrintI(state));
        thread2.start();
    }
}
