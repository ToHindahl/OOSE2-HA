package W3_1_1;

public class CountingSemaphore {
    public int count;
    public CountingSemaphore(int size){
        count = size;
    }
    public synchronized void acquire() throws InterruptedException{
        while(count == 0){
            wait();
        }
        count--;
    }
    public synchronized void release(){
        count++;
        notify();
    }

    public int getCount() {
        return count;
    }
}