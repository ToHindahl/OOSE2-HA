package W1_1;

public class CountingThread extends Thread {

    private int numberOfThread;
    private int max;

    public CountingThread(int numberOfThread, int max) {
        this.numberOfThread = numberOfThread;
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 1; i <= max; i++) {
            System.out.println("Thread " + numberOfThread + " counting " + i);
        }
    }
}

