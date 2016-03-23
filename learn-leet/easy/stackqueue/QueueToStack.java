package easy.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wm on 16/3/23.
 * 队列转换堆栈
 * <p/>
 * 也是使用两个队列来实现堆栈结构
 */
public class QueueToStack {

    public static void main(String[] args) {

    }

    /**
     * q1是专职进出栈的，q2只是个中转站
     * <p/>
     * 入栈：直接入队列q1即可
     * 出栈：把q1的除最后一个元素外全部转移到队q2中,然后把刚才剩下q1中的那个元素出队列。之后把q2中的全部元素转移回q1中
     */
    public class MyStack {


        private Queue<Integer> q1 = new LinkedList<Integer>();
        private Queue<Integer> q2 = new LinkedList<Integer>();

        // Push element x onto stack.
        public void push(int x) {
            q1.add(x);
        }

        // Removes the element on top of the stack.
        public void pop() {
            while (q1.size() > 1) {
                q2.add(q1.poll());
            }

            q1.poll();

            //又回来了
            while (q2.size() > 0) {
                q1.add(q2.poll());
            }

        }

        // Get the top element.
        public int top() {

            while (q1.size() > 1) {
                q2.add(q1.poll());
            }

            int value = q1.poll();

            //又回来了
            while (q2.size() > 0) {
                q1.add(q2.poll());
            }

            q1.add(value);

            return value; // invalid
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return q1.isEmpty();
        }
    }

}
