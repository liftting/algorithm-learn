package middle.list;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/5/10.
 * 148. Sort List
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p/>
 * 1,链表排序， 常数空间， nlogn时间复杂度，  （递归
 * 2，数组排序:符合 快速，快排最坏时间开销O（n^2)，  归并，堆，而对于链表，
 * <p/>
 * 归并排，
 * <p/>
 * 3，要n*logn复杂度的排序，用快排去解，但是很快遇到问题，原因在于这是个linked list，快排经常需要寻找一个节点的前一个节点，不是不能做，而是很麻烦，
 * <p/>
 * 归并排序，这就很容易完成了，因为归并排序不需要往回走。
 * <p/>
 * <p/>
 * 解决：归并， 取中间位置，
 * 划分-合并
 */
public class SortList {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.sortList(ListNodeFactory.buildListNode(new int[]{1, 1, 2, 4, 3, 4, 6})));
    }

    public static class Solution {
        public ListNode sortList(ListNode head) {

            if (head == null || head.next == null)
                return head;

            ListNode fast = head, slow = head;

            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode temp = slow.next;
            slow.next = null;

            ListNode one = sortList(head);
            ListNode two = sortList(temp);

            return mergeTwoLists(one, two);

        }

        //合并，两个已经排序的链表
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            //链表合并，最好创建个头结点
            if (l1 == null) return l2;

            if (l2 == null) return l1;

            ListNode p1 = l1;
            ListNode p2 = l2;

            ListNode head = null;
            ListNode newNode = null;

            if (p1.val > p2.val) {
                newNode = p2;
                head = p2;
                p2 = p2.next;
            } else {
                newNode = p1;
                head = p1;
                p1 = p1.next;
            }

            while (p1 != null && p2 != null) {
                if (p1.val > p2.val) {
                    newNode.next = p2;
                    p2 = p2.next;
                } else {
                    newNode.next = p1;
                    p1 = p1.next;
                }
                newNode = newNode.next;
            }


            if (p1 != null) {
                newNode.next = p1;
            }

            if (p2 != null) {
                newNode.next = p2;
            }

            return head;

        }
    }

}
