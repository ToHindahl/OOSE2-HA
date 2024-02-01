package W2_4;

public class Test {
    public static void main(String[] args) {
        RingQueue ringQueue = new RingQueue(2);
        ringQueue.enqueue(1);
        ringQueue.enqueue(2);
        ringQueue.enqueue(3);
        System.out.println(ringQueue.dequeue());
        System.out.println(ringQueue.dequeue());
    }
}
