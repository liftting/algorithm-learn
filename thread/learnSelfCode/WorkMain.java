package learnSelfCode;


import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 17/5/3.
 */

public class WorkMain {

//    PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>(200, new Comparator<Runnable>() {
//        @Override
//        public int compare(Runnable o1, Runnable o2) {
//
//            RequestData data1 = ((SelfFutureTask) o1).requestData;
//            RequestData data2 = ((SelfFutureTask) o2).requestData;
//
//            return data2.level - data1.level;
//        }
//    });


    PriorityBlockingQueue<Runnable> pqueue = new PriorityBlockingQueue<Runnable>(200);
    LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>();


    ExecutorService executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, pqueue);
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
                data.level = i;
                data.name = "Thread-A:code-" + i + "=level-" + data.level;

                SelfFutureTask futureTask = new SelfFutureTask(new SelfCallable(data));
                executor.submit(futureTask);

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

                SelfFutureTask futureTask = new SelfFutureTask(new SelfCallable(data));
                /**
                 * 1，使用submit后，任务线程池阻塞了，原因？？
                 *
                 * submit 与 execute()区别
                 * 1） 参数
                 * 2）防止，submit会返回Future ，可以控制任务
                 * 3) submit 提交的任务，如果抛出异常，可以反馈到调用方，而execute 可能就将异常吞噬了，
                 */
                executor.submit(futureTask);
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

    private class SelfFutureTask extends FutureTask<ResultData> {
        private RequestData requestData;

        public SelfFutureTask(SelfCallable callable) {
            super(callable);
            requestData = callable.requestData;

        }
    }

    public class SelfCallable implements Callable<ResultData> {

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
//            System.out.println("Exec:" + resultData.result);

            return resultData;
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

}


