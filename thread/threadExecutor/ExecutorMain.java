package threadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 16/12/3.
 */

public class ExecutorMain {

    public static class Result {
        private String name;
        private int value;

        public Result() {

        }

        public Result(String name, int val) {
            this.name = name;
            this.value = val;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class Task implements Callable<Result> {

        private String name;

        public Task(String na) {
            this.name = na;
        }


        @Override
        public Result call() throws Exception {
            System.out.printf("%s: start\n", this.name);
            long dur = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(dur);

            int val = 0;
            for (int i = 0; i < 5; i++) {
                val += (int) (Math.random() * 100);
            }

            System.out.println(this.name + " :Ends");

            return new Result(name, val);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 3; i++) {
            taskList.add(new Task("Task:" + i));
        }

        try {
            System.out.println("Main:time-" + System.currentTimeMillis());
            List<Future<Result>> resultList = executor.invokeAll(taskList);

            System.out.println("Main:time-" + System.currentTimeMillis());
            System.out.println("Main: begin to show result");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
