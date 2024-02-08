package readwrite;

import V4_RWAccess.RWAccessControl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RWAccessDemo {
    public static void main(String[] args) {
        // RWAccessControl erzeugen
        RWAccessControl<Integer> rwAccessControl = new RWAccessControl<Integer>(new Integer[]{1,2,3});
        // Reader erzeugen und starten
        (new Reader(rwAccessControl)).start();
        (new Reader(rwAccessControl)).start();
        (new Reader(rwAccessControl)).start();
        (new Reader(rwAccessControl)).start();
        (new Writer(rwAccessControl)).start();
        (new Writer(rwAccessControl)).start();


        // writer erzeugen und starten
    }

}

class Writer extends Thread{
    private RWAccessControl<Integer> rwAccessControl;

    public Writer(RWAccessControl<Integer> rwAccessControl){
        this.rwAccessControl = rwAccessControl;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i<= 10;i++){
            Map<Integer, Integer> changes = new HashMap();
            changes.put(0, random.nextInt(9));
            changes.put(1, random.nextInt(9));
            changes.put(2, random.nextInt(9));
            try {
                rwAccessControl.write(changes);
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}


class Reader extends Thread{
    private RWAccessControl<Integer> rwAccessControl;

    public Reader(RWAccessControl<Integer> rwAccessControl){
        this.rwAccessControl = rwAccessControl;
    }
    @Override
    public void run() {
        super.run();
        for (int i = 0; i<= 10;i++){
            try {
                Integer[] values = rwAccessControl.read();
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}