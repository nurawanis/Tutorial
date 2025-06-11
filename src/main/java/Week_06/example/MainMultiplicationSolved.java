package Week_06.example;

public class MainMultiplicationSolved extends Thread {

    public static void main(String[] args) {
            MultiplicationThreadSolved t1 = new MultiplicationThreadSolved(1);
            MultiplicationThreadSolved t2 = new MultiplicationThreadSolved(2);
            MultiplicationThreadSolved t3 = new MultiplicationThreadSolved(3);

            t1.start();
            t2.start();
            t3.start();
    }
}
