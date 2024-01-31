package W3_1_2;

import W3_1_1.CountingSemaphore;

import java.util.ArrayList;
import java.util.List;

public class KochenMain {
    public static void main(String[] args) {
        // Erstellen Sie zwei `CountingSemaphore`-Objekte mit den Größen 1 und 1
        CountingSemaphore zwiebelSchneiden = new CountingSemaphore(1);
        CountingSemaphore tomatenSchneiden = new CountingSemaphore(1);
        CountingSemaphore nudelnKochen = new CountingSemaphore(1);
        CountingSemaphore soßeKochen = new CountingSemaphore(1);

        // Erstellen Sie einen Thread, der die Zwiebeln schneidet
        Thread zwiebelSchneidenThread = new Thread(() -> {
            try {
                // Warten, bis die `zwiebelSchneiden`-Semaphore freigegeben wird
                zwiebelSchneiden.acquire();

                // Schneiden Sie die Zwiebeln
                System.out.println("Die Zwiebeln werden geschnitten.");

                // Freigeben der `zwiebelSchneiden`-Semaphore
                zwiebelSchneiden.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Erstellen Sie einen Thread, der die Tomaten schneidet
        Thread tomatenSchneidenThread = new Thread(() -> {
            try {
                // Warten, bis die `tomatenSchneiden`-Semaphore freigegeben wird
                tomatenSchneiden.acquire();

                // Schneiden Sie die Tomaten
                System.out.println("Die Tomaten werden geschnitten.");

                // Freigeben der `tomatenSchneiden`-Semaphore
                tomatenSchneiden.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Erstellen Sie einen Thread, der die Nudeln kocht
        Thread nudelnKochenThread = new Thread(() -> {
            try {
                // Warten, bis die `nudelnKochen`-Semaphore freigegeben wird
                nudelnKochen.acquire();

                // Kochen Sie die Nudeln
                System.out.println("Die Nudeln werden gekocht.");

                // Freigeben der `nudelnKochen`-Semaphore
                nudelnKochen.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Erstellen Sie einen Thread, der die Soße kocht
        Thread soßeKochenThread = new Thread(() -> {
            try {
                // Warten, bis die `soßeKochen`-Semaphore freigegeben wird
                soßeKochen.acquire();
                zwiebelSchneiden.acquire();
                tomatenSchneiden.acquire();
                System.out.println("Die Soße wird gekocht.");
                soßeKochen.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Starten Sie alle Threads
        zwiebelSchneidenThread.start();
        tomatenSchneidenThread.start();
        nudelnKochenThread.start();
        soßeKochenThread.start();

        // Warten Sie, bis alle Threads beendet sind
        try {
            zwiebelSchneidenThread.join();
            tomatenSchneidenThread.join();
            nudelnKochenThread.join();
            soßeKochenThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ausgabe "Das Essen ist fertig!"
        System.out.println("Das Essen ist fertig!");
    }
}
