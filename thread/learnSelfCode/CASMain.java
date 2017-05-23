package learnSelfCode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wm on 17/5/15.
 */

public class CASMain {

    public static void main(String[] args) {
        new CASMain().exec();
    }

    private AtomicInteger number = new AtomicInteger(0);
    private CountDownLatch countDownLatch = new CountDownLatch(3);

    private void inc() {
        number.incrementAndGet();
    }

    private void exec() {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        inc();
                    }
                    countDownLatch.countDown();
                }
            });

            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("in main");

        System.out.println(number);

    }

}
