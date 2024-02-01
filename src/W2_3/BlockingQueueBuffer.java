package W2_3;

import W2_2.Buffer;
import W2_2.Consumer;
import W2_2.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueBuffer<T> extends Buffer<T> {
    private BlockingQueue<T> blockingQueue;

    public BlockingQueueBuffer(int size){
        this.blockingQueue = new LinkedBlockingQueue<>(size);
    }
    public synchronized void put(T value, Producer p) throws InterruptedException {
        while(blockingQueue.remainingCapacity() == 0) {
            wait();
        }

        blockingQueue.offer(value);
        System.out.println(p.getProducerName() + " wrote value "+ value);
        notifyAll();
    }
    public synchronized T get(Consumer c) throws InterruptedException {
        while(blockingQueue.isEmpty()) {
            wait();
        }
        T v = blockingQueue.poll();
        System.out.println(c.getConsumerName() + " read value "+ v);
        notifyAll();
        return v;
    }

}
