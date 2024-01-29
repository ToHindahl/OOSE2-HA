package W1_1;

public class CountingThreadMain {

    public static void main(String[] args) {
        executeCountingThreads(10, 10);
    }

    public static void executeCountingThreads(int numberOfThreads, int max) {
        for (int i = 0; i < numberOfThreads; i++) {
            CountingThread thread = new CountingThread(i + 1, max);
            thread.start();
        }
    }

}
