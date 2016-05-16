package middle.list;

import easy.CommonUtil;
import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/5/16.
 * 86. Partition List
 * <p/>
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p/>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p/>
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * <p/>
 * 1,链表基于当前数据进行划分，
 * 直接交换值，不用修改链表结构
 * 但是：因为涉及到 遍历时，往前移动问题，很不方便，
 * <p/>
 * 2，拆分， 然后合并，
 * 链表换分 的解决办法；
 *
 *
 */
public class PartitionList {

    public static void main(String[] args) {

        Solution s = new Solution();
        ListNodeFactory.showListNode(s.partition(ListNodeFactory.buildListNode(new int[]{5, -5, 0, -8, 3, 8, -8, 0, -1, 3, -8, 7, -4, -8, 2, 5, 9, 4, -5, -6}), -4));

    }

    public static class Solution {
        public ListNode partition(ListNode head, int x) {

            if (head == null) return head;

            ListNode one = new ListNode(Integer.MIN_VALUE);
            ListNode two = new ListNode(Integer.MIN_VALUE);

            ListNode first = one;
            ListNode second = two;

            ListNode next = head;

            while (next != null) {
                if (next.val < x) {
                    first.next = next;
                    first = first.next;
                } else {
                    second.next = next;
                    second = second.next;
                }
                next = next.next;
            }

            //前面已经划分好了，直接接上即可
            first.next = two.next;
            second.next = null;

            return one.next;

        }


    }

}
