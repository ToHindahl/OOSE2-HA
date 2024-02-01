package W2_2;

public class Buffer<T> {

    private boolean available;
    private T data;

    public Buffer() {
        this.available = true;
    }


    public synchronized void put(T data, Producer p) throws InterruptedException {
        while(available){
            System.out.println("Producer " + p + " is waiting to send value " + data);
            wait();
        }
        this.data = data;
        available = true;
        notifyAll();
    }
    public synchronized T get(Consumer c) throws InterruptedException {
        while(available) {
            System.out.println("Consumer " + c + " is waiting to receive value " + data);
            wait();
        }
        available = true;
        notify();
        return data;
    }

}
