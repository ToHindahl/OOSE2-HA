package W2_3;

import java.util.Random;

public class Producer extends Thread{


    private String name;
    private Buffer blockingQueue;

    public Producer(String name, Buffer blockingQueue){
        this.name = name;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            blockingQueue.putWhile(random.nextInt(10), this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProducerName() {
        return name;
    }

}
