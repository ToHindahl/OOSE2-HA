package W5_1;

public class CyclicBarrier {
    private int count;
    private int initialCount;

    public CyclicBarrier(int count) {
        this.count = count;
        this.initialCount = count;
    }

    public synchronized void await() throws InterruptedException {
        count--;
        if (count == 0) {
            notifyAll();
            count = initialCount;
        } else {
            wait();
        }
    }

    public int getParties() {
        return initialCount;
    }

    public int getNumberWaiting() {
        return count;
    }
}
