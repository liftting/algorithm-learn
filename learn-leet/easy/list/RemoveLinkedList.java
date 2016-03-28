package easy.list;

/**
 * Created by wm on 16/3/28.
 * 203. Remove Linked List Elements
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * <p/>
 * 两个指针，走动，
 * 后面一个进行判断，
 */
public class RemoveLinkedList {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.removeElements(ListNodeFactory.buildListNode(new int[]{1, 2, 6, 3, 4, 5, 6}), 1));
    }

    public static class Solution {
        public ListNode removeElements(ListNode head, int val) {

            if (head == null) return head;

            ListNode cHead = new ListNode(0);
            cHead.next = head;

            ListNode first = cHead;
            ListNode second = cHead.next;

            while (second != null) {
                if (second.val == val) {
                    first.next = second.next;
                    second = second.next;
                } else {
                    first = first.next;
                    second = second.next;
                }
            }
            return cHead.next;

        }
    }

}
