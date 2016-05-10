package easy.list;

/**
 * Created by wm on 16/3/21.
 * <p/>
 * 合并两个已经排序的列表
 * <p/>
 * 21. Merge Two Sorted Lists
 */
public class MergeSortLinkList {

    public static void main(String[] args) {
        ListNode p1 = ListNodeFactory.buildListNode(new int[]{2, 4, 6, 8, 10});
        ListNode p2 = ListNodeFactory.buildListNode(new int[]{1, 3, 5, 7, 9, 11, 13});

        Solution s = new Solution();
        ListNodeFactory.showListNode(s.mergeTwoLists(p1, p2));

    }

    public static class Solution {
        /**
         * 遍历，两个索引比较，哪个小，就获取到继续向前走，
         * <p/>
         * 1 3 5 7 9
         * 2 4 6 8 10
         *
         * @param l1
         * @param l2
         * @return
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

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


        public ListNode mergeList(ListNode h1, ListNode h2) {
            ListNode first = new ListNode(Integer.MIN_VALUE);
            ListNode next = first;

            while (h1 != null && h2 != null) {

                if (h1.val < h2.val) {
                    next.next = h1;
                    h1 = h1.next;
                } else {
                    next.next = h2;
                    h2 = h2.next;
                }

                next = next.next;
            }
            if (h1 != null)
                next.next = h1;
            if (h2 != null)
                next.next = h2;
            return first.next;
        }
    }

}
