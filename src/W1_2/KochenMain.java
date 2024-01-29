package W1_2;

import java.util.ArrayList;
import java.util.List;

public class KochenMain {

    public static void main(String[] args) {
        kochen();
        kochen();
        kochen();
        kochen();
    }

    public static void kochen() {

        Thread nudelThread = new Thread(() -> {
            nudelnKochen();
        });

        Thread zwiebelThread = new Thread(() -> {
            zwiebelnScheiden();
        });

        Thread tomatenThread = new Thread(() -> {
           tomatenSchneiden();
        });

        Thread soßenThread = new Thread(() -> {
           soßeKochen();
        });

        nudelThread.start();

        executeBlocking(zwiebelThread, tomatenThread);

        soßenThread.start();

        try {
            nudelThread.join();
            soßenThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        servieren();
    }

    public static void nudelnKochen() {
        System.out.println("Nudeln kochen...");
    }

    public static void zwiebelnScheiden() {
        System.out.println("Zwiebeln schneiden...");
    }

    public static void tomatenSchneiden() {
        System.out.println("Tomaten schneiden...");
    }

    public static void soßeKochen() {
        System.out.println("Soße kochen...");
    }

    public static void servieren() {
        System.out.println("Essen ist fertig ;)");
    }

    public static void executeBlocking(Runnable... runnables) {
        List<Thread> threads = new ArrayList<>();
        for (Runnable runnable : runnables) {
            threads.add(new Thread(runnable));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
