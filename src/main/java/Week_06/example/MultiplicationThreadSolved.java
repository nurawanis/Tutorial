package Week_06.example;

public class MultiplicationThreadSolved extends Thread {
    private int number;

    public MultiplicationThreadSolved(int number) {
        this.number = number;
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Thread " + number + ": " + number + " * " + i + " = " + (number * i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
