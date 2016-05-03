package middle.list;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/4/27.
 * 92. Reverse Linked List II
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p/>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p/>
 * return 1->4->3->2->5->NULL.
 * <p/>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * <p/>
 * 1 2 3 4 5
 * <p/>
 * <p/>
 * 这个是给特定位置的 反转操作，
 * <p/>
 * 1，两个指针，走位数，
 * 2，记住最前面的  和最后面的，
 * <p/>
 * 链表问题，一般容易，两指针来回走，判断方向
 */
public class ReverseListTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.reverseBetween(ListNodeFactory.buildListNode(new int[]{1, 2, 3, 4, 5}), 8, 9));
    }

    public static class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {

            if (head == null) return head;

            ListNode first = head;

            ListNode pre = null;
            ListNode endNext = null;

            ListNode mNode = null;
            ListNode nNode = null;

            int i = 1;
            while (head != null) {
                if (i == m - 1) {
                    pre = head;
                } else if (i == m) {
                    mNode = head;
                } else if (i == n) {
                    nNode = head;
                } else if (i == n + 1) {
                    endNext = head;
                }
                head = head.next;
                i++;
            }

            if (mNode == null && nNode == null) return first;

            ListNode reverNode = reverseList(mNode, nNode, endNext);
            if (pre == null) {
                return reverNode;
            } else {
                pre.next = reverNode;
            }
            return first;

        }

        public ListNode reverseList(ListNode head, ListNode end, ListNode endNext) {
            if (head == null) return head;

            ListNode node = head;
            ListNode next = head.next;
            //需要记录三个节点， 第一个，第二个，第三个，

            while (next != endNext) {
                //3个
                ListNode temp = next.next;
                next.next = node; //反转

                // 重置，方便下次递归
                node = next;
                next = temp;
            }

            head.next = endNext;

            return node;

        }
    }


}
