package W1_1;

public class Threads {


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("t1" + " counting " + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("t2" + " counting " + i);
            }
        });
        t1.start();
        t2.start();
    }

    /**
     * Wenn wir den Code wie oben implementieren und ausführen, erhalten wir folgende Ausgabe auf der Konsole:
     *
     * t1 counting 1
     * t2 counting 1
     * t1 counting 2
     * t2 counting 2
     * t1 counting 3
     * t2 counting 3
     *
     * Wie wir sehen, werden die Ausgaben der beiden Threads nicht in der Reihenfolge ausgegeben, in der sie aufgerufen wurden.
     * Dies liegt daran, dass Threads parallel ausgeführt werden und der Ausführungsverlauf nicht vorhersehbar ist.
     */


}
