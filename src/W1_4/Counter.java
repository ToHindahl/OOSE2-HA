package W1_4;

public class Counter {
        private int counter = 0;
        public void increase() {
            int temp = counter;
            //System.out.println("Increasing counter to " + (temp+1));
            counter = temp + 1;
        }
        @Override
        public String toString() {
            return String.valueOf(counter);
        }
    }
