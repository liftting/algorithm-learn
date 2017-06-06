package learnSelfCode;


import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 17/5/3.
 */

public class WorkMain {

    PriorityBlockingQueue<Runnable> pqueue = new PriorityBlockingQueue<Runnable>();
    LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>();


    ExecutorService executor = new ProThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, pqueue);
//    ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        new WorkMain().exec();

    }

    public void exec() {

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new ExecOne());
        service.submit(new ExecTwo());

    }

    private class ExecOne implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                RequestData data = new RequestData();
                data.level = 12;
                data.name = "Thread-A:code-" + i + "=level-" + data.level;

                Callable callable = new SelfCallable(data);
                executor.submit(callable);

//                try {
//                    ResultData resultData = futureTask.get();
//                    System.out.println("Thread-A:" + resultData.result);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }

            }
        }
    }

    private class ExecTwo implements Runnable {

        @Override
        public void run() {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println("Thread-B:begin to put data");
            for (int i = 0; i < 15; i++) {
                RequestData data = new RequestData();
                data.level = i;
                data.type = 1;

                data.name = "Thread-B:code-" + i + "=level-" + data.level;

                Callable callable = new SelfCallable(data);
                /**
                 * 1，使用submit后，任务线程池阻塞了，原因？？
                 *
                 * submit 与 execute()区别
                 * 1） 参数
                 * 2）防止，submit会返回Future ，可以控制任务
                 * 3) submit 提交的任务，如果抛出异常，可以反馈到调用方，而execute 可能就将异常吞噬了，
                 */
                executor.submit(callable);
//                executor.execute(futureTask);

//                try {
//                    ResultData resultData = futureTask.get();
//                    System.out.println("Thread-B:" + resultData.result);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
            }

        }
    }

    public class SelfCallable implements Callable<ResultData>, Comparable<SelfCallable> {

        public RequestData requestData;

        public SelfCallable(RequestData data) {
            this.requestData = data;
        }

        @Override
        public ResultData call() throws Exception {

            if (requestData.type == 0) {
                Thread.sleep(500);
            } else {
                Thread.sleep(1000);
            }

            System.out.println("Exec:" + requestData.name);

            ResultData resultData = new ResultData();
            resultData.result = "Exec over";

            System.out.println("Exec:" + resultData.result);

            return resultData;
        }

        @Override
        public int compareTo(SelfCallable o) {
            return this.requestData.level - o.requestData.level;
        }
    }

    public class RequestData {
        public int level = 0;
        public String name = "";
        public int type = 0;
    }

    public class ResultData {
        public String result = "";
    }


    //支持 带优先级比较的 ThreadPool
    private class ProThreadPoolExecutor extends ThreadPoolExecutor {

        public ProThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public ProThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public ProThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public ProThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            return new ComparableFutureTask<T>(callable);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
            return new ComparableFutureTask<T>(runnable, value);
        }

        protected class ComparableFutureTask<V> extends FutureTask<V> implements Comparable<ComparableFutureTask<V>> {

            private Object compareObject;

            public ComparableFutureTask(Callable<V> callable) {
                super(callable);
                compareObject = callable;
            }

            public ComparableFutureTask(Runnable runnable, V result) {
                super(runnable, result);
                compareObject = runnable;
            }

            @Override
            public int compareTo(ComparableFutureTask<V> o) {
                if (this == o) return 0;
                if (o == null) return -1;

                if (compareObject != null && o.compareObject != null) {
                    if (compareObject.getClass().equals(o.compareObject.getClass())) {
                        //字节码相等
                        if (compareObject instanceof Comparable) {
                            return ((Comparable) compareObject).compareTo(o.compareObject);
                        }
                    }
                }

                return 0;
            }
        }
    }

}


