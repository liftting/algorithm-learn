package syncOther;

import java.util.concurrent.Semaphore;

import jdk.nashorn.internal.scripts.JO;

/**
 * Created by wm on 16/12/3.
 */

public class SemaphoreMain {

    public static class PrintQueue {
        private final Semaphore semaphore;

        public PrintQueue() {
            semaphore = new Semaphore(1, true); //0表示已经被暂用
        }

        public void print(Object document) {
            try {
                semaphore.acquire();
                long dur = (long) (Math.random() * 10);
                System.out.printf("%s : Print during %d second\n", Thread.currentThread().getName(), dur);

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static class Job implements Runnable {

        private PrintQueue queue;

        public Job(PrintQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            queue.print(new Object());
        }
    }

    public static void main(String[] args) {
        PrintQueue queue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(queue), "Thread" + i);
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }

}
