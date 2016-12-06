package collection;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wm on 16/12/3.
 */

public class LinkedMain {

    public static class AddTask implements Runnable {

        private ConcurrentLinkedDeque<String> list;

        public AddTask(ConcurrentLinkedDeque list) {
            this.list = list;
        }

        @Override
        public void run() {
            System.out.printf("%s begin to add Element\n", Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                list.add(name + ":Element -" + i);
            }
        }
    }

    public static class RemoveTask implements Runnable {

        private ConcurrentLinkedDeque<String> list;

        public RemoveTask(ConcurrentLinkedDeque list) {
            this.list = list;
        }

        @Override
        public void run() {

            for (int i = 0; i < 5; i++) {
                list.pollFirst();
                list.pollLast();
            }
        }
    }

    public static void main(String[] args) {
        // Create a ConcurrentLinkedDeque to work with it in the example
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<String>();
        // Create an Array of 100 threads
        Thread threads[] = new Thread[4];

        // Create 100 AddTask objects and execute them as threads
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);

        // Wait for the finalization of the threads
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Write to the console the size of the list
        System.out.printf("Main: Size of the List: %d\n", list.size());

        // Create 100 PollTask objects and execute them as threads
        for (int i = 0; i < threads.length; i++) {
            RemoveTask task = new RemoveTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);

        // Wait for the finalization of the threads
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Write to the console the size of the list
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }


}

