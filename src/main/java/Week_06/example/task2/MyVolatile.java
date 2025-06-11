package Week_06.example.task2;

import java.util.Scanner;

public class MyVolatile extends Thread{

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        System.out.println("Press ENTER to stop the thread...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();

        t.shutdown();
    }
}