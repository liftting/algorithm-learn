package learnSelfCode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 17/5/3.
 */

public class Test {

    public static void main(String[] args) {
        PriorityExecutor executorService = (PriorityExecutor) PriorityExecutor.newFixedThreadPool(1);
        executorService.submit(getRunnable("1"), 1);
        executorService.submit(getRunnable("3"), 3);
        executorService.submit(getRunnable("2"), 2);
        executorService.submit(getRunnable("5"), 5);
        executorService.submit(getRunnable("4"), 4);

        executorService.shutdown();
        try {
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Runnable getRunnable(final String id) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    static class PriorityExecutor extends ThreadPoolExecutor {

        public PriorityExecutor(int corePoolSize, int maximumPoolSize,
                                long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        //Utitlity method to create thread pool easily

        public static ExecutorService newFixedThreadPool(int nThreads) {
            return new PriorityExecutor(nThreads, nThreads, 0L,
                    TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());
        }
        //Submit with New comparable task

        public Future<?> submit(Runnable task, int priority) {
            return super.submit(new ComparableFutureTask(task, null, priority));
        }
        //execute with New comparable task

        public void execute(Runnable command, int priority) {
            super.execute(new ComparableFutureTask(command, null, priority));
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            return (RunnableFuture<T>) callable;
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
            return (RunnableFuture<T>) runnable;
        }
    }

    static class ComparableFutureTask<T> extends FutureTask<T> implements Comparable<ComparableFutureTask<T>> {

        volatile int priority = 0;

        public ComparableFutureTask(Runnable runnable, T result, int priority) {
            super(runnable, result);
            this.priority = priority;
        }

        public ComparableFutureTask(Callable<T> callable, int priority) {
            super(callable);
            this.priority = priority;
        }

        @Override
        public int compareTo(ComparableFutureTask<T> o) {
            return Integer.valueOf(priority).compareTo(o.priority);
        }
    }
}