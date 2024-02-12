package W4_2;


public class Lock {
    protected boolean isLocked() {
        return isLocked;
    }

    protected void setLocked(boolean locked) {
        isLocked = locked;
    }

    private boolean isLocked = false;

    protected Thread getLockingThread() {
        return lockingThread;
    }

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

