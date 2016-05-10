package middle.list;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/5/10.
 * <p/>
 * 82. Remove Duplicates from Sorted List II
 * <p/>
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p/>
 * For example,
 * Given 1->2->3->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * <p/>
 * 1, 有重复节点的前一个节点，
 * 2，判断要进行比较的节点，当前节点和后一个节点，
 * 3，注意如果节点开头就有重复时，要设置开头的节点
 */
public class RemoveDuplicateSortListTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.deleteDuplicates(ListNodeFactory.buildListNode(new int[]{1, 1})));
    }

    public static class Solution {
        public ListNode deleteDuplicates(ListNode head) {

            if (head == null) return head;

            //创建一个头，好处理逻辑
            ListNode first = new ListNode(-1);
            first.next = head;


            ListNode cur = head;
            ListNode pre = first;
            boolean hasMore = false;

            while (cur != null) {
                ListNode next = cur.next;
                if (next != null) {
                    //重复
                    if (cur.val == next.val) {
                        hasMore = true;
                    } else {
                        if (hasMore) {
                            pre.next = cur.next;
                            hasMore = false;
                        } else {
                            pre = cur;
                        }
                    }
                }
                cur = next;
            }

            if (hasMore) {
                //最后的是重复的，
                pre.next = null;
            }

            return first.next;

        }
    }

}
