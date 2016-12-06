package sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wm on 16/12/3.
 */

public class ThreadWaitMain {

    public static class Storage {
        private int maxSize;
        private List<Date> storage;

        public Storage() {
            maxSize = 10;
            storage = new LinkedList<Date>();
        }

        public synchronized void set() {
            while (storage.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(new Date());
            System.out.printf("set %d ", storage.size());
            notifyAll();
        }

        public synchronized void get() {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ((LinkedList) storage).poll();
            System.out.printf("get %d ", storage.size());
            notifyAll();
        }

    }

    private static class Producer implements Runnable {

        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.set();
            }
        }
    }

    private static class Consumer implements Runnable {

        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.get();
            }
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread thread = new Thread(new Consumer(storage));
        Thread thread2 = new Thread(new Producer(storage));

        thread.start();
        thread2.start();
    }

}
