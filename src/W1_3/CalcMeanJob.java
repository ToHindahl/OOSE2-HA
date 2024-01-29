package W1_3;

public class CalcMeanJob implements Runnable {

    private int[] intArray;
    private int fromIndex;
    private int toIndex;
    private double result;

    @Override
    public void run() {
        double sum = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            sum += intArray[i];
        }
        result = sum / (toIndex - fromIndex);
    }

    public double getResult() {
        return result;
    }

    public CalcMeanJob(int[] intArray, int fromIndex, int toIndex) {
        this.intArray = intArray;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }
}
