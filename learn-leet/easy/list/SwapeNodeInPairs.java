package easy.list;

/**
 * Created by wm on 16/3/24.
 * <p/>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p/>
 * 节点，两两进行交换
 */
public class SwapeNodeInPairs {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.swapPairs(ListNodeFactory.buildListNode()));
    }

    public static class Solution {
        /**
         * 记住四个节点，
         * pre  one two thrid
         *
         * @param head
         * @return
         */
        public ListNode swapPairs(ListNode head) {

            if (head == null) return head;

            ListNode createHead = head;
            if (head.next != null) createHead = head.next;

            ListNode first = head;
            ListNode second = head.next;

            ListNode pre = null;

            while (first != null && second != null) {
                ListNode thrid = second.next;
                second.next = first;
                first.next = thrid;

                if (pre != null) {
                    pre.next = second;
                }

                pre = first;

                first = thrid;
                if (thrid == null || thrid.next == null) {
                    second = null;
                } else {
                    second = thrid.next;
                }
            }

            return createHead;
        }


    }

}
