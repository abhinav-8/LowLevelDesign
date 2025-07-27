package Multithreading;

public class MonitorLock {

    public synchronized void task1() {
        try{
            System.out.println("Inside task1");
            Thread.sleep(10000);
            System.out.println("Completed synchronized task1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void task2() {
        System.out.println("Inside task2 before synchronized");
        synchronized (this) {
            System.out.println("Completed synchronized task2");
        }
    }

    public void task3() {
        System.out.println("Inside task3");
    }
}
