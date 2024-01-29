package W2_2;

public class Consumer extends Thread{

    private String name;
    private Buffer buffer;

    public Consumer(String name, Buffer buffer){
        this.name = name;
        this.buffer = buffer;
    }


}
