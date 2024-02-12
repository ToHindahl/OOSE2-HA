package W4_2;

public class ReentrantLock {
    private int lockCount = 0;
    private Thread lockingThread;
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        if (lockingThread == currentThread) {
            lockCount++;
            return;
        }
        while (isLocked) {
            wait();
        }
        isLocked = true;
        lockingThread = currentThread;
        lockCount = 1;
    }

    public synchronized void unlock() throws Exception {
        if (Thread.currentThread() != lockingThread) {
            throw new Exception("Current thread is not the owner");
        }
        lockCount--;
        if (lockCount == 0) {
            lockingThread = null;
            isLocked = false;
            notify();
        }
    }
}
