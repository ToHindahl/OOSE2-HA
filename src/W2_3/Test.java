package W2_3;

import W2_2.Consumer;
import W2_2.Producer;

public class Test {
    public static void main(String[] args) {
        BlockingQueueBuffer blockingQueue = new BlockingQueueBuffer(2);
        (new Producer("p1", blockingQueue)).start();
        (new Producer("p2", blockingQueue)).start();
        (new Producer("p3", blockingQueue)).start();
        (new Producer("p4", blockingQueue)).start();
        (new Producer("p5", blockingQueue)).start();
        (new Consumer("c1", blockingQueue)).start();
        (new Consumer("c2", blockingQueue)).start();
    }
}
