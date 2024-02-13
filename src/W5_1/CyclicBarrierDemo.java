package W5_1;

import java.util.Random;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Runnable r = () -> {
            try {
                Random random = new Random();
                System.out.println(Thread.currentThread() + " starting.");
                Thread.sleep(random.nextInt(5000));
                System.out.println(Thread.currentThread()
                        + " 1st sync point. How many are waiting: "
                        + (cyclicBarrier.getParties() - cyclicBarrier.getNumberWaiting()));
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " SYNCED");
                Thread.sleep(random.nextInt(5000));
                System.out.println(Thread.currentThread()
                        + " 2nd sync point. How many are waiting: "
                        + (cyclicBarrier.getParties() - cyclicBarrier.getNumberWaiting()));
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " SYNCED again and done.");
            } catch (Exception e) {}
        };
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }

}
