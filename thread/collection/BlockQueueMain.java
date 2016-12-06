package collection;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by wm on 16/12/3.
 */

public class BlockQueueMain {


    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(3);

        Client client = new Client(list);
        Thread thread = new Thread(client);
        thread.start();

        //主线程从队列中获取， 阻塞，如果没有回阻塞，
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String request = list.take();
                System.out.printf("Main: get: %s at %s. Size: %d\n", request, new Date(), list.size());
            }
            TimeUnit.MILLISECONDS.sleep(2000);
        }

        System.out.printf("Main: End of the program.\n");
    }


    public static class Client implements Runnable {

        private LinkedBlockingDeque<String> requestList;

        public Client(LinkedBlockingDeque<String> requestList) {
            this.requestList = requestList;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    StringBuilder request = new StringBuilder();
                    request.append(i);
                    request.append(":");
                    request.append(j);
                    try {
                        requestList.put(request.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Client: put %s at %s.\n", request, new Date());
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("Client: End.\n");
        }


    }

}
