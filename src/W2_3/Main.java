package W2_3;

public class Main {
    public static void main(String[] args) {
        Buffer blockingQueue = new Buffer(2);
        (new Producer("p1", blockingQueue)).start();
        (new Producer("p2", blockingQueue)).start();
        (new Producer("p3", blockingQueue)).start();
        (new Producer("p4", blockingQueue)).start();
        (new Producer("p5", blockingQueue)).start();
        (new Consumer("c1", blockingQueue)).start();
        (new Consumer("c2", blockingQueue)).start();
    }
}
