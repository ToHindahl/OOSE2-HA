package W4_2;


public class Lock {
    private boolean isLocked = false;
    private Thread lockingThread;
    public synchronized void lock() throws InterruptedException{
        while(isLocked) wait();
        isLocked = true;
        lockingThread = Thread.currentThread();
    }
    public synchronized void unlock() throws Exception {
        if(Thread.currentThread() != lockingThread)
            throw new Exception("Current thread no owner");
        isLocked = false;
        notify();
    }
}

