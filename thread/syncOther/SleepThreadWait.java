package syncOther;

/**
 * Created by wm on 17/4/26.
 * sleep wait() 资源释放问题s
 */

public class SleepThreadWait {

    public Object lock = new Object();


    public static void main(String[] args) {
        SleepThreadWait sleepThreadWait = new SleepThreadWait();
        sleepThreadWait.exec();
    }

    public void exec() {

        new Thread(new SleepTask()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("in main thread");

                synchronized (lock) {
                    System.out.println("in main, and get lock");
                }

            }
        }).start();
    }

    public class SleepTask implements Runnable {
        @Override
        public void run() {
            System.out.println("in sleep task");

            synchronized (lock) {

                System.out.println("in sleep task,begin sleep");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("in sleep task,over sleep");


            }

        }
    }

}
