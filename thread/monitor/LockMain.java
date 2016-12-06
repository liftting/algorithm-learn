package monitor;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wm on 16/12/6.
 */

public class LockMain {


    public static class MyLock extends ReentrantLock {

        public String getOwnerName() {
            if (this.getOwner() == null) {
                return "None";
            }
            return this.getOwner().getName(); //锁被哪个线程获取到了
        }

        /**
         * This method returns the list of the threads queued in the lock
         *
         * @return The list of threads queued in the Lock
         */
        public Collection<Thread> getThreads() {
            return this.getQueuedThreads();
        }
    }

    public static class MyTask implements Runnable {

        private MyLock lock;

        public MyTask(MyLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
            /*
             * Acquire the lock
			 */
                lock.lock();
                System.out.printf("%s: Get the Lock.\n", Thread.currentThread().getName());
            /*
             * Sleeps the thread 500 milliseconds
			 */
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                /*
                 * Free the lock
				 */
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        MyLock lock = new MyLock();
        Thread threads[] = new Thread[5];

		/*
         * Create and start five threads
		 */
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask(lock);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < 15; i++) {
            System.out.printf("Main: Logging the Lock\n");
            System.out.printf("************************\n");
            if (lock.hasQueuedThreads()) { //判断是否有线程正在等待 这个线程
                System.out.printf("Lock: Queue Length: %d\n", lock.getQueueLength());
                System.out.printf("Lock: Queued Threads: ");
                Collection<Thread> lockedThreads = lock.getThreads();
                for (Thread lockedThread : lockedThreads) {
                    System.out.printf("%s ", lockedThread.getName());
                }
                System.out.printf("\n");
            }

            System.out.printf("Lock: Fairness: %s\n", lock.isFair());
            System.out.printf("Lock: Locked: %s\n", lock.isLocked());
            System.out.printf("************************\n");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
