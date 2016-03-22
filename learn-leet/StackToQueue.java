import java.util.Stack;

/**
 * Created by wm on 16/3/21.
 * <p/>
 * 232. Implement Queue using Stacks
 * 使用栈结构来实现队列，
 * <p/>
 * 两个栈来模拟队列处理
 */
public class StackToQueue {


    public static class MyQueue {

        //队列，先进先出
        //栈后进先出，

        private Stack<Integer> first;

        private Stack<Integer> second;

        public MyQueue() {
            first = new Stack<Integer>();
            second = new Stack<Integer>();
        }

        // Push element x to the back of queue.
        public void push(int x) {
            //先检查second是否包含，有，移动到first，再入
            if (!second.empty()) {
                //convert to first
                while (!second.empty()) {
                    first.push(second.pop());
                }

                first.push(x);

            } else {
                first.push(x);
            }

        }

        // Removes the element from in front of queue.
        public void pop() {
            //先检查second有，有直接出，没有，移动first到second

            if (!second.empty()) {
                second.pop();
            } else {
                while (!first.empty()) {
                    second.push(first.pop());
                }

                second.pop();

            }

        }

        // Get the front element.
        public int peek() {
            if (!second.empty()) {
                return second.peek();
            } else {
                while (!first.empty()) {
                    second.push(first.pop());
                }
                return second.peek();
            }
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return first.empty() && second.empty();
        }
    }

}
