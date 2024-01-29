package W1_4;

public class TurnstileMain {
        public static void main(String[] args) {
            Counter counter = new Counter();
            Turnstile t1 = new Turnstile(counter, 100);
            Turnstile t2 = new Turnstile(counter, 100);
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Value of counter is " + counter);
        }
}

/**
 *
 * 1. Bei der ersten Ausführung der main-Methode ergibt die Ausgabe der println-Anweisung in Zeile 42 den Wert 200.
 * Dies ist wie erwartet, da die beiden Turnstile-Objekte jeweils 100 Personen zählen.
 *
 * 2. Wenn die Werte für max in Zeilen 32 und 33 auf 1000 oder 10000 geändert werden, ergibt die Ausgabe der println-Anweisung in Zeile 42 den Wert 2000 oder 20000.
 * Dies ist ebenfalls wie erwartet, da die beiden Turnstile-Objekte jeweils 1000 oder 10000 Personen zählen.
 *
 * 3. Wenn die println-Anweisung in einkommentiert wird, ergibt die Ausgabe der println-Anweisung in Zeile 42 immer noch den Wert 200.
 * Dies ist darauf zurückzuführen, dass die Variable counter nicht mehr angezeigt wird, bevor sie geändert wird. Daher ist das Ergebnis der Änderung nicht sichtbar.
 * Jedoch laufen die Ausgaben der zwei Threads durcheinander.
 *
 *
 */


