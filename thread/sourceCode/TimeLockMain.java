package sourceCode;

/**
 * Created by wm on 17/5/10.
 */

public class TimeLockMain {

    private TimeLock lock = new TimeLock();

    public static void main(String[] args) {
        new TimeLockMain().exec();
    }

    private void exec() {
        class Worker extends Thread {
            public void run() {
                lock.lock();

                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            Worker worker = new Worker();
            worker.start();
        }

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
