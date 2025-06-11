package Week_09.example;

public class VolatileFlagExample {

        // Shared flag and lock object
        private static boolean running = true;

        // Lock for synchronization

        private static final Object lock = new Object();

        // Synchronized method to check the flag
        private static boolean isRunning() {
            synchronized (lock) {
                return running;
            }
        }

        // Synchronized method to stop the worker
        private static void stopRunning() {
            synchronized (lock) {
                running = false;
            }
        }

        public static void main(String[] args) {
            // Worker thread
            Thread worker = new Thread(() -> {
                System.out.println("Worker thread started...");
                while (isRunning()) {
                    // simulate work
                }
                System.out.println("Worker thread stopped.");
            });

            worker.start();

            // Main thread sleeps for 2 seconds before stopping the worker
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Main thread stopping worker...");
            stopRunning(); // Signal the worker thread to stop
        }
    }

