package W2_2;

public class Buffer {

    private boolean full;
    private int data;

    public Buffer() {
        this.full = false;
    }

    public synchronized void put(int data) {
        while(full) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            full = true;
            notify();
        }
    }

    public synchronized int get() {
        while(!full) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        full = false;
        notify();
        return data;
    }

}
