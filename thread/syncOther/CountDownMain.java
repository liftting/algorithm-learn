package syncOther;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 16/12/3.
 */

public class CountDownMain {

    public static class Video implements Runnable {

        private final CountDownLatch controller;

        public Video(CountDownLatch latch) {
            controller = latch;
        }

        private void arrive(String name) {
            System.out.printf("%s has arrived.\n", name);
            controller.countDown();

            System.out.printf("Video: waiting for %d users.\n", controller.getCount());
        }

        @Override
        public void run() {
            try {
                System.out.printf("%s: thread begin to enter video, but wait().\n", Thread.currentThread().getName());
                controller.await();
                System.out.printf("%s: all user has comeing.\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class User implements Runnable {

        private Video video;
        private String name;

        public User(Video video, String na) {
            this.video = video;
            this.name = na;
        }

        @Override
        public void run() {
            long dur = (long) (Math.random() * 10);
            try {
                TimeUnit.SECONDS.sleep(dur);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            video.arrive(name);
        }
    }

    public static void main(String[] agrs) {
        Video video = new Video(new CountDownLatch(10));

        for(int i=0;i<5;i++) {
            new Thread(video).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new User(video, "User:" + i)).start();
        }

    }

}
