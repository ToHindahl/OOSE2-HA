package W2_2;

import W2_2.Buffer;

import java.util.Random;

public class Producer extends Thread{


    private String name;
    private Buffer buffer;

    public Producer(String name, Buffer buffer){
        this.name = name;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            buffer.put(random.nextInt(10), this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProducerName() {
        return name;
    }

}
