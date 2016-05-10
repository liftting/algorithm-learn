package middle.list;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/5/9.
 * <p/>
 * 61. Rotate List
 * <p/>
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * <p/>
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * <p/>
 * 1, 链表处理，记录几个节点的位置
 * 2，使用栈结构存储节点，
 */
public class RotateList {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.rotateRight(ListNodeFactory.buildListNode(new int[]{1}), 1));
    }

    public static class Solution {
        public ListNode rotateRight(ListNode head, int k) {

            if (head == null || k <= 0) return head;

            ListNode one = head;
            ListNode two = head;
            ListNode twoPre = null;
            ListNode onePre = null;

            ListNode temp = head;

            int len = 0;
            while (temp != null) {
                len++;
                temp = temp.next;
            }

            int i = k % len; //长度判断，有可能超了，取余数

            if (i <= 0) return head;

            while (one != null && i > 0) {
                i--;
                one = one.next;
            }

            while (one != null) {
                twoPre = two;
                onePre = one;

                one = one.next;
                two = two.next;
            }

            // two 到后面是要反转到前面的，
            twoPre.next = null;
            onePre.next = head;

            return two;

        }
    }

}
