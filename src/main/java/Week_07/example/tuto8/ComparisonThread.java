package Week_07.example.tuto8;

public class ComparisonThread {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }).start();
        }
        long endTime = System.nanoTime();
        System.out.println("Normal thread = " + (endTime - startTime) / 1e9 + " seconds");


        startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (ComparisonThread.class) {
                    try { Thread.sleep(10); } catch (InterruptedException e) {}
                }
            }).start();
        }
        endTime = System.nanoTime();
        System.out.println("Synchronized thread = " + (endTime - startTime) / 1e9 + " seconds");
    }
}

