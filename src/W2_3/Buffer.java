package W2_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer<T> {
    private BlockingQueue<T> blockingQueue;

    public Buffer(int size){
        this.blockingQueue = new LinkedBlockingQueue<>(size);
    }

    /**
    * funktioniert aktuell nicht
    * */


    public synchronized void put(T value, Producer p) throws InterruptedException {
        blockingQueue.put(value);
        System.out.println(p.getProducerName() + " wrote value "+ value);
        notifyAll();
    }

    /**
     * Durch die while Schleife wird sichergestellt, dass der Producer nicht
     * versucht zu schreiben, wenn der Buffer voll ist.
     * die Methode queue.offer hat dies bereits implementiert
     */

    public synchronized void putWhile(T value, Producer p) throws InterruptedException {
        while(blockingQueue.remainingCapacity() == 0) {
            wait();
        }

        blockingQueue.offer(value);
        System.out.println(p.getProducerName() + " wrote value "+ value);
        notifyAll();
    }

    /**
     * funktioniert aktuell nicht
     * */
    public synchronized T get(Consumer c) throws InterruptedException {
        T v = blockingQueue.take();
        System.out.println(c.getConsumerName() + " read value "+ v);
        notifyAll();
        return v;
    }

    /**
     * Durch die while Schleife wird sichergestellt, dass der Consumer nicht
     * versucht zu lesen, wenn der Buffer leer ist.
     * die Methode queue.take hat dies bereits implementiert
     */
    public synchronized T getWhile(Consumer c) throws InterruptedException {
        while(blockingQueue.isEmpty()) {
            wait();
        }
        T v = blockingQueue.poll();
        System.out.println(c.getConsumerName() + " read value "+ v);
        notifyAll();
        return v;
    }

}
