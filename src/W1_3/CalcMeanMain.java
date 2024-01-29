package W1_3;

public class CalcMeanMain {


    public static void main(String[] args) {
        int[] intArray = new int[100000000];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i;
        }
        benchmark(intArray, 1);
        benchmark(intArray, 2);
        benchmark(intArray, 4);
        benchmark(intArray, 8);
        benchmark(intArray, 16);
    }

    public static void benchmark(int[] intArray, int numberofThreads) {
        long startTime = System.currentTimeMillis();
        calcMean(intArray, numberofThreads);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms with " + numberofThreads + " threads");
    }

    public static double calcMean(final int[] intArray, int numberofThreads) {
        CalcMeanJob[] calcMeanJobs = createCalcMeanJob(intArray, numberofThreads);
        Thread[] threads = new Thread[calcMeanJobs.length];
        for (int i = 0; i < calcMeanJobs.length; i++) {
            threads[i] = new Thread(calcMeanJobs[i]);
            threads[i].start();
        }
        double sum = 0.0;

        try {
            for (int i = 0; i < calcMeanJobs.length; i++) {
                threads[i].join();
                sum += calcMeanJobs[i].getResult();
            }
            return sum / calcMeanJobs.length;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sum / calcMeanJobs.length;
    }

    private static CalcMeanJob[] createCalcMeanJob(int[] intArray, int numberofThreads) {
        CalcMeanJob[] calcMeanJobs = new CalcMeanJob[numberofThreads];
        int arrayLength = intArray.length;
        int fragmentLength = arrayLength / numberofThreads;

        int currentBeginning = 0;
        int currentEnd = fragmentLength - 1;
        for (int i = 0; i < numberofThreads; i++) {
            calcMeanJobs[i] = new CalcMeanJob(intArray, currentBeginning, currentEnd);
            currentBeginning += fragmentLength;
            currentEnd += fragmentLength;
            if( currentEnd > (arrayLength - 1) ) {
                currentEnd = arrayLength - 1;
            }
        }
        return calcMeanJobs;
    }

}
