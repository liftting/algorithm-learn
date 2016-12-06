package base;

import java.util.Random;

public class ThreadGroupLearn {

    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("wmGroup");
        MyTask task = new MyTask();

        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(group, task);
            thread.start();
        }
    }

    public static class MyThreadGroup extends ThreadGroup {
        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("thread %s has exception", t.getId());

            interrupt();
        }

    }

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            int result;
            Random random = new Random(Thread.currentThread().getId());
            while (true) {
                result = 1000 / (int) (random.nextDouble() * 1000); //分母0就抛异常
                if (Thread.currentThread().isInterrupted()) {
                    System.out.printf("%s : %d", Thread.currentThread().getId(), result);
                    return;
                }
            }
        }
    }

}