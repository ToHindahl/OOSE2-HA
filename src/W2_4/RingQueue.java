package W2_4;

public class RingQueue<T> {

    private int capacity;

    private int pointerRead = 0;
    private int pointerWrite = 0;

    private int used = 0;
    private T[] data;

    public RingQueue(int capacity) {
        this.capacity = capacity;
        this.data =  (T[])new Object[capacity];
    }

    public void enqueue(T t) {
        data[pointerWrite] = t;
        pointerWrite++;
        if(pointerWrite >= capacity)
            pointerWrite = 0;
        if(used < capacity)
            used++;
    }

    public T dequeue() {
        if(pointerRead < 0)
            throw new IndexOutOfBoundsException();
        if(pointerRead >= used)
            throw new IndexOutOfBoundsException();
        pointerRead = pointerWrite - (pointerRead + 1);
        if(pointerRead < 0)
            pointerRead += capacity;
        return data[pointerRead];
    }
}