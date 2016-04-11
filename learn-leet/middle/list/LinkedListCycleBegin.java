package middle.list;

import easy.CommonUtil;
import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/4/8.
 * <p/>
 * 142. Linked List Cycle II
 * <p/>
 * 求环开始的节点
 * Do not modify the linked list.
 * Can you solve it without using extra space?
 * <p/>
 * 1 2 3 4
 * 3 5
 * 3 4 5 是个环， 3 是开始点
 * <p/>
 * 快 2步  慢：1步
 * 3-1
 * 5 - 2
 * 4 - 3
 * 3 - 4
 * <p/>
 * 5 - 5 //碰面
 * <p/>
 * 慢回退到开头 1位置
 * 然后都各走一步
 * 4 - 2
 * 3 - 3 // 再次碰面地方就是起点
 * <p/>
 * 原理：
 * 1，快指针两步，慢指针一步，
 * 2，如果两指针碰面，那么 慢指针移动到头部
 * 3，然后两指针都改成各走一步的
 * 4，再次碰面就是环起点
 */
public class LinkedListCycleBegin {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNodeSingle(s.detectCycle(ListNodeFactory.buildCycleListNode()));

    }

    public static class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return head;
            ListNode x = head;
            ListNode y = head;

            do {
                x = x.next;
                y = y.next;
                if (y == null) return null;

                y = y.next;

                if (y == null) return null;

                if (x == y) break;

            } while (x != null && y != null);

            //整个链都是环时，要特殊处理下
            if (x == head) return head;
            x = head;

            do {
                x = x.next;
                y = y.next;

                if (x == y) return x;

            } while (x != null && y != null);

            return null;

        }
    }

}
