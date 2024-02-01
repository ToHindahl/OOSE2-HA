package W2_4;

public class RingQueueWrong<T> {

    private int capacity;

    private int pointerRead = 0;
    private int pointerWrite = 0;
    private T[] data;

    public RingQueueWrong(int capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity];
    }

    public void enqueue(T value) {
        data[pointerWrite] = value;
        if(pointerWrite == capacity - 1) {
            pointerWrite = 0;
        } else {
            pointerWrite++;
        }
    }

    public T dequeue() {
        T value = data[pointerRead];
        if(value == null) {
            return null;
        }
        if(pointerRead == capacity - 1) {
            pointerRead = 0;
        } else {
            pointerRead++;
        }
        return value;
    }

}
