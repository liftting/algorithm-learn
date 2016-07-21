package diffcult.list;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/7/20.
 * 23. Merge k Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 很多个listNode 链表，要进行合并，排序，
 * 两个的时候是双指针来处理，
 * 解法：
 * 1，暴力处理，直接定义 k 个头指针来进行遍历处理，
 * 直接合并前两个后，再拿这个和下一个进行合并， 循环n次
 * <p/>
 * 2，两两合并，
 * 不断的划分规模，nlogn
 * 分治法，
 */
public class MergeKSortList {

    public static void main(String[] args) {
        ListNode p1 = ListNodeFactory.buildListNode(new int[]{2, 4, 6, 8, 10});
        ListNode p2 = ListNodeFactory.buildListNode(new int[]{1, 3, 5, 7, 9, 11, 13});
        ListNode p3 = ListNodeFactory.buildListNode(new int[]{15, 17, 19, 20});
        ListNode p4 = ListNodeFactory.buildListNode(new int[]{11, 14, 16, 18, 22, 25, 28});

        ListNode[] datas = new ListNode[]{p1, p2, p3, p4};

        Solution s = new Solution();
        ListNodeFactory.showListNode(s.mergeKLists(datas));

    }

    public static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length <= 0) return null;

            int n = lists.length;

            while (n > 1) {
                // 合并一半
                int k = (n + 1) / 2; // n=3 0 -2
                for (int i = 0; i < n / 2; i++) {
                    lists[i] = mergeList(lists[i], lists[i + k]);
                }
                n = k;
            }

            return lists[0];

        }


        /**
         * 将两个合并成一个的，
         *
         * @param h1
         * @param h2
         * @return
         */
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
