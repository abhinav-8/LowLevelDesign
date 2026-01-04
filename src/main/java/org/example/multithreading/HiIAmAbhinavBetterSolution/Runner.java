package org.example.multithreading.HiIAmAbhinavBetterSolution;

import lombok.SneakyThrows;

import static org.example.multithreading.HiIAmAbhinavBetterSolution.Turn.*;

public class Runner implements Runnable {

    @Override
    @SneakyThrows
    public void run() {

        State state = new State(HI);

        Thread t2 = new Thread(new PrintWord(state, I, AM));
        t2.start();

        Thread t1 = new Thread(new PrintWord(state, HI, I));
        t1.start();

        Thread t4 = new Thread(new PrintWord(state, ABHINAV, null));
        t4.start();

        Thread t3 = new Thread(new PrintWord(state, AM, ABHINAV));
        t3.start();
    }
}
