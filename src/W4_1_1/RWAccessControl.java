package W4_1_1;

import W3_1_1.BinarySemaphore;

import java.util.Arrays;
import java.util.Map;

import static java.lang.Thread.sleep;

public class RWAccessControl<T> {
    private T[] data;
    private int activeReaders = 0;
    private int waitingReaders = 0;
    private boolean writerWriting = false;
    private BinarySemaphore readSemaphore;
    private BinarySemaphore writeSemaphore;

    public RWAccessControl(T[] data) {
        this.data = data;
        this.readSemaphore = new BinarySemaphore();
        this.writeSemaphore = new BinarySemaphore();
    }

    public void write(Map<Integer, T> changes) throws InterruptedException {
        writeSemaphore.acquire(); // Exclusive access for write

        // Wait if there are active readers or a writer is already writing
        while (activeReaders > 0 || writerWriting) {
            writeSemaphore.release();
            readSemaphore.acquire();
            writeSemaphore.acquire();
        }

        writerWriting = true;
        writeSemaphore.release();

        for (int i = 0; i < data.length; i++) {
            if (changes.containsKey(i)) {
                data[i] = changes.get(i);
            }
        }

        writeSemaphore.acquire();
        System.out.println("Wrote changes " + changes);
        writerWriting = false;
        writeSemaphore.release();
        readSemaphore.release();
    }

    public T[] read() throws InterruptedException {
        readSemaphore.acquire();
        waitingReaders++;
        System.out.println(waitingReaders + " many readers waiting");
        readSemaphore.release();

        writeSemaphore.acquire();
        while (writerWriting) {
            writeSemaphore.release();
            readSemaphore.acquire();
            writeSemaphore.acquire();
        }

        readSemaphore.acquire();
        waitingReaders--;
        activeReaders++;
        System.out.println(activeReaders + " many readers active");
        readSemaphore.release();
        writeSemaphore.release();

        T[] returnValue = Arrays.copyOf(data, data.length); // Lesen
        sleep(50);

        readSemaphore.acquire();
        activeReaders--;
        System.out.println("Read values " + Arrays.toString(returnValue));
        /**
         *         if (activeReaders == 0) {
         *             writeSemaphore.release();
         *         }
         */
        readSemaphore.release();

        return returnValue;
    }
}
