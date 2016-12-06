package sync;

import java.io.File;
import java.io.InterruptedIOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wm on 16/12/3.
 * 多条件 Condition接口
 * <p>
 * 条件的必须在锁 Lock.lock() 和 unlock()之间
 * 条件的 await()时，会自动释放这个条件绑定的锁，其他线程才可以获取这个锁执行操作，
 * <p>
 * 当一个线程调用条件的 signal() signalAll()
 * 多个在该条件上挂起的咸菜被唤醒，
 * <p>
 * 一个条件的 await() 调用， 若不调用他的 signal() ，那么这个线程就永久休眠
 */

public class ConditionMain {

    public static class FileMock {
        private String[] content;
        private int index;

        /**
         * @param size   几个数据段
         * @param length 每个段数据长度
         */
        public FileMock(int size, int length) {
            content = new String[size];
            for (int i = 0; i < size; i++) {
                StringBuffer sb = new StringBuffer(length);
                for (int j = 0; j < length; j++) {
                    int id = (int) Math.random() * 255;
                    sb.append(id);
                }
                content[i] = sb.toString();
            }
        }

        public boolean hasMoreLines() {
            return index < content.length;
        }

        public String getLine() {
            if (this.hasMoreLines()) {
                return content[index++];
            }
            return null;
        }
    }

    public static class Buffer {
        private LinkedList<String> buffer;
        private int maxSize;

        private ReentrantLock lock;
        private Condition lines;
        private Condition space;


        public Buffer(int maxSize) {
            this.maxSize = maxSize;
            buffer = new LinkedList<String>();

            lock = new ReentrantLock();
            lines = lock.newCondition();
            space = lock.newCondition();

        }

        public void insert(String line) {
            lock.lock();
            try {
                while (buffer.size() == maxSize) {
                    space.await(); //没有空，等待，是否锁 (await() 属于lock wait()属于 object)
                }
                buffer.offer(line);
                System.out.printf("%s:inserted line: %d\n", Thread.currentThread().getName(), buffer.size());
                lines.signalAll();//通知所有获取的线程，可以获取了，因为数据已经填充了

            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }

        }

        public String get() {
            String line = null;
            lock.lock();

            try {
                while (buffer.size() == 0) {
                    lines.await();
                }
                System.out.printf("%s:line readed: %d\n", Thread.currentThread().getName(), buffer.size());
                line = buffer.poll();
                space.signalAll();

            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
            return line;
        }


    }


    public static class Producer implements Runnable {

        private FileMock mock;
        private Buffer buffer;

        public Producer(FileMock mock, Buffer buffer) {
            this.mock = mock;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (mock.hasMoreLines()) {
                buffer.insert(mock.getLine());
            }
        }
    }

    public static class Consumer implements Runnable {
        private Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            String line = buffer.get();
            System.out.println(line);
            processLine(line);
        }

        private void processLine(String line) {
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);


        Thread thread2 = new Thread(new Producer(mock, buffer));
        Consumer sumer[] = new Consumer[3];

        for (int i = 0; i < 3; i++) {
            sumer[i] = new Consumer(buffer);
            new Thread(sumer[i]).start();
        }

        thread2.start();

    }

}
