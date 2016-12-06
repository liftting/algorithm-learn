package change;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 16/12/3.
 */

public class MyThreadFactoryMain {

    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory threadFactory = new MyThreadFactory("wm");
        ExecutorService executor = Executors.newCachedThreadPool(threadFactory);

        MyTask task = new MyTask();

		/*
         * Submit the task
		 */
        executor.submit(task);

		/*
         * Shutdown the executor
		 */
        executor.shutdown();

		/*
		 * Wait for the finalization of the executor
		 */
        executor.awaitTermination(1, TimeUnit.DAYS);

		/*
		 * Write a message indicating the end of the program
		 */
        System.out.printf("Main: End of the program.\n");

    }

    public static class MyTask implements Runnable {

        /**
         * Main method of the task. It sleeps the thread for two seconds
         */
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static class MyThread extends Thread {

        /**
         * Creation date of the thread
         */
        private Date creationDate;

        /**
         * Start date of the thread
         */
        private Date startDate;

        /**
         * Finish date of the thread
         */
        private Date finishDate;

        /**
         * Constructor of the class. It establishes the value of the creation date attribute
         *
         * @param target Runnable object that this thread is going to execute
         * @param name   Name of the thread
         */
        public MyThread(Runnable target, String name) {
            super(target, name);
            setCreationDate();
        }

        /**
         * Main method of the thread. Stores the start and finish date of the thread and calls
         * the run() method of its parent class
         */
        @Override
        public void run() {
            setStartDate();
            super.run();
            setFinishDate();
            System.out.printf("Thread: %s\n", toString());
        }

        /**
         * Method that establish the creation date of the thread
         */
        public void setCreationDate() {
            creationDate = new Date();
        }

        /**
         * Method that establish the start date of the thread
         */
        public void setStartDate() {
            startDate = new Date();
        }

        /**
         * Method that establish the finish date of the thread
         */
        public void setFinishDate() {
            finishDate = new Date();
        }

        /**
         * Method that calculates the execution time of the thread as the difference
         * between the finish date and the start date.
         *
         * @return
         */
        public long getExecutionTime() {
            long ret;
            ret = finishDate.getTime() - startDate.getTime();
            return ret;
        }

        /**
         * Method that generates a String with information about the creation date and the
         * execution time of the thread
         */
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(getName());
            buffer.append(": ");
            buffer.append(" Creation Date: ");
            buffer.append(creationDate);
            buffer.append(" : Running time: ");
            buffer.append(getExecutionTime());
            buffer.append(" Milliseconds.");
            return buffer.toString();
        }
    }

    public static class MyThreadFactory implements ThreadFactory {

        /**
         * Attribute to store the number of threads created by the Factory
         */
        private int counter;

        /**
         * Prefix to use in the name of the threads created by the Factory
         */
        private String prefix;

        /**
         * Constructor of the class. Initializes its attributes
         *
         * @param prefix Prefix to use in the name of the threads
         */
        public MyThreadFactory(String prefix) {
            this.prefix = prefix;
            counter = 1;
        }

        /**
         * Method that create a new MyThread object to execute the Runnable
         * object that receives as parameter
         */
        @Override
        public Thread newThread(Runnable r) {
            MyThread myThread = new MyThread(r, prefix + "-" + counter);
            counter++;
            return myThread;
        }
    }

}
