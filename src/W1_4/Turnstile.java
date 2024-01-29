package W1_4;

public class Turnstile extends Thread {
        private Counter counter;
        private int maxValue;
        public Turnstile(Counter counter, int maxValue) {
            this.counter = counter;
            this.maxValue = maxValue;
        }
        public void run() {
            for (int i = 0; i < this.maxValue; i++) {
                //System.out.println("Detected person; counter: " + counter);
                this.counter.increase();
            }
        }
    }
