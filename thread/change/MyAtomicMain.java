package change;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wm on 16/12/5.
 */

public class MyAtomicMain {


    public static class MyAtomic extends AtomicInteger {

        private int maxNumber;

        public MyAtomic(int maxNumber) {
            set(0);
            this.maxNumber = maxNumber;
        }

        public boolean up() {
            for (; ; ) {
                int value = get();
                if (value == maxNumber) {
                    return false;
                } else {
                    int newValue = value + 1;
                    System.out.println("MyAtomic:up begin to set...");
                    boolean change = compareAndSet(value, newValue); //尝试去修改 ,如果等于 value，就尝试去修改成 newValue值，
                    if (change) {
                        //已经被修改了
                        System.out.println("MyAtomic:value has change up..." + get());
                        return true;
                    }
                }
            }
        }

        public boolean down() {
            for (; ; ) {
                int value = get();
                if (value == 0) {
                    return false;
                } else {
                    int newValue = value - 1;
                    System.out.println("MyAtomic:down begin to set...");
                    boolean change = compareAndSet(value, newValue);
                    if (change) {
                        System.out.println("MyAtomic:value has change down..." + get());
                        return true;
                    }
                }
            }
        }

    }

    public static class Sensor1 implements Runnable {

        /**
         * Counter of cars in the parking
         */
        private MyAtomic counter;

        /**
         * Constructor of the class. It initializes its attributes
         *
         * @param counter Counter of cars in the parking
         */
        public Sensor1(MyAtomic counter) {
            this.counter = counter;
        }


        /**
         * Main method of the sensor. Simulates the traffic in the door of the parking
         */
        @Override
        public void run() {
            counter.up();
            counter.up();
            counter.up();
            counter.up();
            counter.down();
            counter.down();
            counter.down();
            counter.up();
            counter.up();
            counter.up();
        }

    }


    public static class Sensor2 implements Runnable {

        /**
         * Counter of cars in the parking
         */
        private MyAtomic counter;

        /**
         * Constructor of the class. It initializes its attributes
         *
         * @param counter Counter of cars in the parking
         */
        public Sensor2(MyAtomic counter) {
            this.counter = counter;
        }

        /**
         * Main method of the sensor. Simulates the traffic in the door of the parking
         */
        @Override
        public void run() {
            counter.up();
            counter.down();
            counter.down();
            counter.up();
            counter.up();
            counter.up();
            counter.up();
            counter.up();
            counter.up();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyAtomic counter = new MyAtomic(5);

		/*
         * Create and launch two sensors
		 */
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);

        Thread thread1 = new Thread(sensor1);
        Thread thread2 = new Thread(sensor2);

        thread1.start();
        thread2.start();

		/*
         * Wait for the finalization of the threads
		 */
        thread1.join();
        thread2.join();

		/*
         * Write in the console the number of cars in the parking
		 */
        System.out.printf("Main: Number of cars: %d\n", counter.get());

		/*
         * Writ a message indicating the end of the program
		 */
        System.out.printf("Main: End of the program.\n");
    }

}
