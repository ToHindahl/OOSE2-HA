package W2_3;

public class Consumer extends Thread{

    private String name;
    private Buffer blockingQueue;

    public Consumer(String name, Buffer blockingQueue){
        this.name = name;
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run(){
        try {
            Object value = blockingQueue.getWhile(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConsumerName() {
        return name;
    }
}
