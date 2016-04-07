package middle.paixu;

import easy.CommonUtil;
import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/4/6.
 * 147. Insertion Sort List
 * 单链表的插入排序
 * <p/>
 * 链表，不能从后往前判断，
 * <p/>
 * 2-3-5-1-4
 * <p/>
 * head
 */
public class InsertSortLinkedList {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.insertionSortList(ListNodeFactory.buildListNode(new int[]{2, 3, 5, 1, 4})));
    }

    public static class Solution {
        public ListNode insertionSortList(ListNode head) {
            ListNode cHead = new ListNode(Integer.MIN_VALUE);

            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                insert(cHead, cur);
                cur = next;
            }

            return cHead.next;

        }

        public void insert(ListNode pre, ListNode node) {
            ListNode cur = pre;
            while (cur.next != null && cur.next.val < node.val) {
                cur = cur.next;
            }

            // 插入到cur 的后边
            node.next = cur.next;
            cur.next = node;

            // -1 2

        }
    }

}
