package Multithreading;

public class MonitorLockMain{

    public static void main(String[] args) {
        MonitorLock obj = new MonitorLock();
        Thread thread1 = new Thread(() -> {obj.task1();});
        Thread thread2 = new Thread(() -> {obj.task2();});
        Thread thread3 = new Thread(() -> {obj.task3();});

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
