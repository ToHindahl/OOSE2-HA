package W4_2;

import java.util.LinkedList;
import java.util.Queue;

public class FairReentrantLock {

    private int lockCount = 0;
    private Thread lockingThread;
    private boolean isLocked = false;
    private Queue<Thread> waitingQueue = new LinkedList<>();

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        if(lockingThread == currentThread){
            lockCount++;
            return;
        }
        waitingQueue.add(currentThread);
        while (waitingQueue.peek() != currentThread || isLocked) {
            wait();
        }
        waitingQueue.remove();
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
            isLocked = false;
            lockingThread = null;
            notifyAll();

        }
    }
}
