package W3_3;

import W3_1_1.BinarySemaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        DiningTable table = new DiningTable(numPhilosophers);

        for (int i = 0; i < numPhilosophers; i++) {
            new Philosopher(i, table).start();
        }
    }

    static class DiningTable {
        private static BinarySemaphore[] forks;
        private static int numPhilosophers;

        public DiningTable(int numPhilosophers) {
            DiningTable.numPhilosophers = numPhilosophers;
            forks = new BinarySemaphore[numPhilosophers];

            for (int i = 0; i < numPhilosophers; i++) {
                forks[i] = new BinarySemaphore(); // Jede Gabel ist anfangs verfügbar
            }
        }

        public static BinarySemaphore getFork(int index) {
            return forks[index];
        }

        public static int getNumPhilosophers() {
            return numPhilosophers;
        }
    }

    static class Philosopher extends Thread {
        private int id;
        private DiningTable table;

        public Philosopher(int id, DiningTable table) {
            this.id = id;
            this.table = table;
        }

        private void think() throws InterruptedException {
            System.out.println("Philosopher " + id + " is thinking.");
            //Thread.sleep((long) (Math.random() * 1000)); // Zufällige Pause für das Denken
        }

        private void eat() throws InterruptedException {
            BinarySemaphore leftFork = table.getFork(id);
            BinarySemaphore rightFork = table.getFork((id + 1) % table.getNumPhilosophers());

            System.out.println("Philosopher " + id + " is trying to pick up forks.");

            leftFork.acquire();
            rightFork.acquire();

            System.out.println("Philosopher " + id + " is eating.");

            // Simuliere das Essen
            //Thread.sleep((long) (Math.random() * 1000));

            // Gabeln freigeben, wenn das Essen beendet ist
            leftFork.release();
            rightFork.release();

            System.out.println("Philosopher " + id + " finished eating.");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    think();
                    eat();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
