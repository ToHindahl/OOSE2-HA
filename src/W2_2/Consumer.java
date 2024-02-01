package W2_2;

public class Consumer extends Thread{

    private String name;
    private Buffer buffer;

    public Consumer(String name, Buffer blockingQueue){
        this.name = name;
        this.buffer = blockingQueue;
    }
    @Override
    public void run(){
        try {
            Object value = buffer.get(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConsumerName() {
        return name;
    }
}
