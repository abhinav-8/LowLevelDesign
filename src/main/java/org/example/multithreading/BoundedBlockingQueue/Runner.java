package org.example.multithreading.BoundedBlockingQueue;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Scanner;

@AllArgsConstructor
public class Runner implements Runnable {

    private final int capacity;

    @Override
    @SneakyThrows
    public void run() {

        BoundedBlockingQueue q = new BoundedBlockingQueue(new ArrayList<Integer>(), capacity);
        Scanner scanner = new Scanner(System.in);

        while(true) {

            String input = scanner.nextLine();
            String[] items = input.split(" ");

            if(input.equals("exit")) {
                System.exit(0);
                break;
            }


            if(items[0].equals("en")) {
                new Thread(() -> {
                    int value = Integer.parseInt(items[1]);
                    q.enqueue(value);
                }).start();

//              new Thread(new Runnable() {
//
//                    @SneakyThrows
//                    @Override
//                    public void run() {
//                        int value = Integer.parseInt(items[1]);
//                        q.enqueue(value);
//                    }
//                }).start();

            }

            else if(items[0].equals("de")) {
                new Thread(() -> q.dequeue()).start();

//                new Thread(new Runnable() {
//
//                    @SneakyThrows
//                    @Override
//                    public void run() {
//                        q.dequeue();
//                    }
//                }).start();

            }
        }
    }
}
