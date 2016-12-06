package change;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 16/12/3.
 */

public class MyExecutorMain {

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor(2, 4, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        List<Future<String>> results = new ArrayList<Future<String>>();

		/*
         * Create and submit to the executor 10 tasks
		 */
        for (int i = 0; i < 10; i++) {
            SleepTwoSecondsTask task = new SleepTwoSecondsTask();
            Future<String> result = executor.submit(task);
            results.add(result);
        }

        for (int i = 0; i < 5; i++) {
            try {
                String result = results.get(i).get();
                System.out.printf("Main: Result for Task %d : %s\n", i, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        for (int i = 5; i < 10; i++) {
            try {
                String result = results.get(i).get();
                System.out.printf("Main: Result for Task %d : %s\n", i, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

		/*
         * Wait for the finalization of the Executor
		 */
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		/*
		 * Write a message indicating the end of the program
		 */
        System.out.printf("Main: End of the program.\n");


    }

    public static class SleepTwoSecondsTask implements Callable<String> {

        /**
         * Main method of the tasks. It only sleeps the current thread for two seconds
         */
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(2);
            return new Date().toString();
        }

    }

    public static class MyExecutor extends ThreadPoolExecutor {

        private ConcurrentHashMap<String, Date> startTimes;

        public MyExecutor(int corePoolSize, int maximumPoolSize,
                          long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            startTimes = new ConcurrentHashMap<String, Date>();
        }

        /**
         * This method is called to finish the execution of the Executor. We write statistics
         * about the tasks executed in it
         */
        @Override
        public void shutdown() {
            System.out.printf("MyExecutor: Going to shutdown.\n");
            System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
            System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
            System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
            super.shutdown();
        }

        /**
         * This method is called to finish the execution of the Executor immediately. We write statistics
         * about the tasks executed in it
         */
        @Override
        public List<Runnable> shutdownNow() {
            System.out.printf("MyExecutor: Going to immediately shutdown.\n");
            System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
            System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
            System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
            return super.shutdownNow();
        }

        /**
         * This method is executed before the execution of a task. We store the start date in the HashMap
         */
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.printf("MyExecutor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
            startTimes.put(String.valueOf(r.hashCode()), new Date());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            Future<?> result = (Future<?>) r; //转换得到执行结果
            try {
                System.out.printf("*********************************\n");
                System.out.printf("MyExecutor: A task is finishing.\n");
                System.out.printf("MyExecutor: Result: %s\n", result.get());


                Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
                Date finishDate = new Date();
                long diff = finishDate.getTime() - startDate.getTime();
                System.out.printf("MyExecutor: Duration: %d\n", diff);
                System.out.printf("*********************************\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
