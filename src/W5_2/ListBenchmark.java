package W5_2;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListBenchmark {
    private static final int THREAD_COUNT = 10;
    private static final int ITERATIONS = 1000;
    private static final int ELEMENTS = 100;

    public static void main(String[] args) {
        List<Integer> synchronizedList = Collections.synchronizedList(new java.util.ArrayList<>());
        List<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();

        System.out.println("Running benchmark for synchronized list...");
        runBenchmark(synchronizedList);
        System.out.println("Running benchmark for CopyOnWriteArrayList...");
        runBenchmark(copyOnWriteList);
    }

    private static void runBenchmark(List<Integer> list) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    for (int k = 0; k < ELEMENTS; k++) {
                        list.add(k);
                    }
                    for (int k = 0; k < ELEMENTS; k++) {
                        list.get(k);
                    }
                    for (int k = 0; k < ELEMENTS; k++) {
                        list.remove(0);
                    }
                }
            });
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

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
