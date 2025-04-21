package Week_04.example;

public class ThreadRunnable {

    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        System.out.println(t.getState());
        System.out.println("hello");
    }
}
