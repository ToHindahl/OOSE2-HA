package V4_RWAccess;

import java.util.Arrays;
import java.util.Map;

public class RWAccessControl<T> {

    private T[] data;
    private int activeReaders = 0;
    private int waitingReaders = 0;
    //private int activeWriters = 0;
    private boolean writerWriting = false;

    public RWAccessControl(T[] data){
        this.data = data;
    }

    public void write(Map<Integer, T> changes) throws InterruptedException{
        synchronized (this){
            // warte, wenn
            // (1) reader warten
            // (2) reader aktiv sind
            // (3) writer aktiv sind
            while(waitingReaders>0
                    || activeReaders>0
                    || writerWriting){
                wait();
            }
            writerWriting = true;
        }

        for (int i=0; i<data.length; i++){
            if(changes.containsKey(i)){
                data[i] = changes.get(i);
            }
        }

        synchronized (this){
            System.out.println("Wrote changes " + changes);
            writerWriting = false;
            notifyAll();
        }
    }

    public T[] read() throws InterruptedException{
        synchronized (this){
            //warten wenn Schreiber schreibt. .. while wait()...
            waitingReaders++;
            System.out.println(waitingReaders + " many readers waiting");
            while(writerWriting){
                wait();
            }
            //vor dem Lesen: activesReader++
            waitingReaders--;
            activeReaders++;
            System.out.println(activeReaders + " many readers active");
        }


        // nicht synchronized
        T[] returnValue = data;

        synchronized (this){
            //nach dem Lesen: activesReader--, notifyAll();
            activeReaders--;
            System.out.println("Read values " + Arrays.toString(returnValue));
            notifyAll();
        }
        return returnValue;
    }

}
