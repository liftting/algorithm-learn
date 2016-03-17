/**
 * Created by wm on 16/3/17.
 * 83. Remove Duplicates from Sorted List
 * <p/>
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * <p/>
 */
public class RemoveDuplicate {

    public static void main(String[] args) {
        Solution s = new Solution();

        TreeNodeFactory.showListNode(s.deleteDuplicates(TreeNodeFactory.buildListNode(new int[]{1, 1, 2, 3, 3, 4, 4, 4, 5})));


    }

    public static class Solution {
        public ListNode deleteDuplicates(ListNode head) {

            if (head == null) return head;

            ListNode first = head;
            ListNode second = head.next;

            while (second != null) {

                if (first.val == second.val) {
                    //处理移除
                    first.next = second.next;
                    second = second.next;
                } else {
                    first = second;
                    second = second.next;
                }
            }

            return head;

        }

    }

}
