package W5_1;

import java.util.Random;
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable r = () -> {
             try {
                 Random random = new Random();
                 Thread.sleep(random.nextInt(3000));
                 System.out.println(Thread.currentThread() + " done.");
                 countDownLatch.countDown();
             } catch (Exception e) {}
        };
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        try {
            System.out.println("Waiting for threads to complete");
            countDownLatch.await();
            System.out.println("All threads finished");
        } catch (InterruptedException e) {}
    }
}
