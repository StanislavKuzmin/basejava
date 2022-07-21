package com.urase.webapp;

public class Deadlock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = createThread(lock1, lock2);
        Thread thread2 = createThread(lock2, lock1);
        thread1.start();
        thread2.start();
    }

    private static Thread createThread(Object object1, Object object2) {
        return new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (object1) {
                System.out.println("Run monitor on " + object1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("Run monitor on " + object2);
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        });
    }
}
