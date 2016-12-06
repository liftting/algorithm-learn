package sync;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wm on 16/12/3.
 */

public class ReadLockMain {


    public static class PriceInfo {
        private double price;
        private ReadWriteLock lock;


        public PriceInfo() {
            price = 1.0;
            lock = new ReentrantReadWriteLock(); //这里又一个参数用来配置公平性质的， 默认false ，不公平竞争
            // 因为当多个线程在等待锁时， 这时的选择是有策略的，如果配置了 true公平竞争，那就会选择等待时间最长的线程
        }

        public double getPrice1() {
            lock.readLock().lock();
            double result = price;
            lock.readLock().unlock();
            return result;
        }

        public void setPrice(double price) {
            lock.writeLock().lock();
            this.price = price;
            lock.writeLock().unlock();
        }

    }


    private static class Reader implements Runnable {

        private PriceInfo priceInfo;

        public Reader(PriceInfo priceInfo) {
            this.priceInfo = priceInfo;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.printf("%s : begin to read price : %f\n", Thread.currentThread().getName(), priceInfo.getPrice1());
            }
        }
    }

    private static class Writer implements Runnable {

        private PriceInfo priceInfo;

        public Writer(PriceInfo priceInfo) {
            this.priceInfo = priceInfo;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.printf("%s : begin to write price\n", Thread.currentThread().getName());
                priceInfo.setPrice(Math.random() * 10);
                System.out.printf("%s : begin to write price over\n", Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        PriceInfo priceInfo = new PriceInfo();
        Thread thread = new Thread(new Writer(priceInfo));
        Thread thread2 = new Thread(new Reader(priceInfo));

        thread.start();
        thread2.start();
    }

}
