package W3_2;

import W3_1_1.CountingSemaphore;

import java.util.LinkedList;

public class LinkedBlockingQueue<T> {

    private CountingSemaphore emptySemaphor = new CountingSemaphore(Integer.MAX_VALUE);
    private CountingSemaphore fullSemaphor = new CountingSemaphore(0);
    private CountingSemaphore mutex = new CountingSemaphore(1);

    private LinkedList<T> queue = new LinkedList<>();

    public void put(T item) throws InterruptedException {
        emptySemaphor.acquire();
        mutex.acquire();
        queue.add(item);
        mutex.release();
        fullSemaphor.release();
    }

    public T get() throws InterruptedException {
        fullSemaphor.acquire();
        mutex.acquire();
        T item = queue.remove();
        mutex.release();
        emptySemaphor.release();
        return item;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}