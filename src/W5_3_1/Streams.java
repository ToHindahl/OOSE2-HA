package W5_3_1;

import java.util.stream.IntStream;

public class Streams {

    public static void main(String[] args) {
        int normalTime = runBenchmark(() -> {
            IntStream.rangeClosed(0, 1_000_000).average().getAsDouble();
        });

        int parallelTime = runBenchmark(() -> {
            IntStream.rangeClosed(0, 1_000_000).parallel().average().getAsDouble();
        });

        System.out.println("Normal: " + normalTime + "ms for 1_000_000 elements");
        System.out.println("Parallel: " + parallelTime + "ms for 1_000_000 elements");

        normalTime = runBenchmark(() -> {
            IntStream.rangeClosed(0, 1_000_000_000).average().getAsDouble();
        });

        parallelTime = runBenchmark(() -> {
            IntStream.rangeClosed(0, 1_000_000_000).parallel().average().getAsDouble();
        });

        System.out.println("Normal: " + normalTime + "ms for 1_000_000_000 elements");
        System.out.println("Parallel: " + parallelTime + "ms for 1_000_000_000 elements");

        normalTime = runBenchmark(() -> {
            IntStream.rangeClosed(0, 1_000).average().getAsDouble();
        });

        parallelTime = runBenchmark(() -> {
            IntStream.rangeClosed(0, 1_000).parallel().average().getAsDouble();
        });

        System.out.println("Normal: " + normalTime + "ms for 1_000 elements");
        System.out.println("Parallel: " + parallelTime + "ms for 1_000 elements");

        /**
         * Normal: 10ms for 1_000_000 elements
         * Parallel: 16ms for 1_000_000 elements
         * Normal: 252ms for 1_000_000_000 elements
         * Parallel: 30ms for 1_000_000_000 elements
         * Normal: 0ms for 1_000 elements
         * Parallel: 0ms for 1_000 elements
         *
         * Daraus lässt sich schließen, dass parallel processing bei großen Datenmengen
         * effizienter ist, bei kleinen Datenmengen jedoch nicht.
         */


    }

    public static int runBenchmark(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }
}
