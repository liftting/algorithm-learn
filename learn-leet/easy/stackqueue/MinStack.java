package easy.stackqueue;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/29.
 * <p/>
 * 155. Min Stack
 * <p/>
 * stack 数组内部实现，
 * <p/>
 * 双向链表也可以实现，
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        CommonUtil.show(stack.getMin());
        stack.pop();
        CommonUtil.show(stack.top());
        stack.pop();
    }

    public static class Node {
        int n;
        int min;
        Node pre;

        public Node(int x) {
            n = x;
        }
    }

    private Node current;

    public void push(int x) {
        Node create = new Node(x);
        //每次添加时，都记录下目前stack的最小值，存放到当前栈中，
        if (current != null) {
            create.pre = current;
            create.min = Math.min(x, current.min);
        } else {
            //null
            create.min = x;
        }
        current = create;
    }

    public void pop() {
        if (current != null) {
            current = current.pre;
        }
    }

    public int top() {
        if (current != null) {
            return current.n;
        }
        return -1;
    }

    public int getMin() {
        if (current == null) return -1;
        return current.min;
    }
}
