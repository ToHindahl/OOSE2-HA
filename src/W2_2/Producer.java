package W2_2;

import java.util.Random;

public class Producer extends Thread{

    public static final int N = 5;

    private String name;
    private Buffer buffer;

    public Producer(String name, Buffer buffer){
        this.name = name;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(!isInterrupted()){
            try {
                buffer.put(random.nextInt(), this);
            } catch (InterruptedException e) {
                break; // terminate thread.
            }
        }
        System.out.println("Producer " + name + " done!");
    }


}
