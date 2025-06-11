package Week_08.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class TrafficLightSimulation {
        public static void main(String[] args) {
            TrafficLightController controller = new TrafficLightController();

            for (int i = 0; i < 4; i++) {
                new TrafficLight(controller, i).start();
            }
        }
    }

    class TrafficLightController {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private int currentJunction = 0; // 0 = North, 1 = East, 2 = South, 3 = West

        public void controlLight(int junctionId) throws InterruptedException {
            while (true) {
                lock.lock();
                try {
                    while (junctionId != currentJunction) {
                        condition.await(); // Wait until it's this junction's turn
                    }

                    // GREEN phase
                    System.out.println("Junction " + junctionId + ": GREEN");
                    Thread.sleep(5000);

                    // YELLOW phase
                    System.out.println("Junction " + junctionId + ": YELLOW");
                    Thread.sleep(2000);

                    // RED phase
                    System.out.println("Junction " + junctionId + ": RED");

                    // Move to next junction
                    currentJunction = (currentJunction + 1) % 4;
                    condition.signalAll(); // Wake up all waiting threads
                } finally {
                    lock.unlock();
                }

                Thread.sleep(1000); // Small delay before re-checking
            }
        }
    }

    class TrafficLight extends Thread {
        private final TrafficLightController controller;
        private final int junctionId;

        public TrafficLight(TrafficLightController controller, int junctionId) {
            this.controller = controller;
            this.junctionId = junctionId;
        }

        public void run() {
            try {
                controller.controlLight(junctionId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


