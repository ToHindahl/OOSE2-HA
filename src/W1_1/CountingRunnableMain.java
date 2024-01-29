package W1_1;

public class CountingRunnableMain {

    public static void main(String[] args) {
        executeCountingThreads(10, 10);
    }

    public static void executeCountingThreads(int numberOfThreads, int max) {
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            CountingRunnable runnable = new CountingRunnable(i + 1, max);
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
    }
}
