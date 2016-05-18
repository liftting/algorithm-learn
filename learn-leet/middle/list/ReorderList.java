package middle.list;

import java.util.Stack;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/5/17.
 * 143. Reorder List
 * <p/>
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p/>
 * You must do this in-place without altering the nodes' values.
 * <p/>
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * <p/>
 * 链表问题，基本 两指针，
 * 创建表头
 * <p/>
 * 1 2 3 4 5 6 7
 * <p/>
 * 1 7 2 6 3 5 4
 * <p/>
 * 1 2 3 4 5 6
 * 1 6 2 5 3 4
 * <p/>
 * <p/>
 * 1，涉及到链表从后往前，  使用stack存储起来，后入前出
 * 2，插入式的，
 */
public class ReorderList {

    public static void main(String[] args) {
        Solution s = new Solution();
//        ListNodeFactory.showListNode(s.reorderList(ListNodeFactory.buildListNode(new int[]{1, 2})));
    }

    public static class Solution {
        public void reorderList(ListNode head) {

            if (head == null) return;

            Stack<ListNode> stack = new Stack<ListNode>();//反方向

            ListNode cur = head;
            int len = 0;
            while (cur != null) {
                len++;
                stack.push(cur);
                cur = cur.next;
            }

            ListNode twoItem = head; //正方向
            ListNode pre = null;

            int i = 0;

            while (twoItem != null) {

                if (len % 2 == 0) {
                    ListNode curNode = stack.peek();
                    if (twoItem == curNode) {
                        curNode.next = null;
                        break;
                    }
                } else {
                    //
                    if (i > len / 2) {
                        pre.next = null;
                        break;
                    }
                }

                if (pre != null) {
                    ListNode node = stack.pop();
                    pre.next = node;
                    node.next = twoItem;
                }

                pre = twoItem;
                twoItem = twoItem.next;
                i++;

            }

        }
    }


}
