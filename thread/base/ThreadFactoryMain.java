package base;

import java.util.concurrent.ThreadFactory;

/**
 * Created by wm on 16/12/2.
 */

public class ThreadFactoryMain {


    public static class MyThreadFactory implements ThreadFactory {

        private String name;
        private int counter;

        public MyThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, name + "-" + counter);
            counter++;
            return thread;
        }
    }

}
