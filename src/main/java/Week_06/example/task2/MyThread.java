package Week_06.example.task2;

import java.util.Scanner;

class MyThread extends Thread {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Thread is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread is stopping...");
    }

    public void shutdown() {
        running = false;
    }
}
