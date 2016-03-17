/**
 * Created by wm on 16/3/15.
 * 206. Reverse Linked List
 * <p/>
 * 链表反转，
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode root = TreeNodeFactory.buildListNode();

        Solution s = new Solution();
        ListNode node = s.reverseList(root);

        TreeNodeFactory.showListNode(node);

    }

    public static class Solution {
        /**
         * 1->2->3->4
         * <p/>
         * 1<-2<-3<-4
         *
         * @param head
         * @return
         */
        public ListNode reverseList(ListNode head) {
            if (head == null) return head;

            ListNode node = head;
            ListNode next = head.next;
            //需要记录三个节点， 第一个，第二个，第三个，

            while (next != null) {
                //3个
                ListNode temp = next.next;
                next.next = node; //反转

                // 重置，方便下次递归
                node = next;
                next = temp;
            }

            head.next = null;

            return node;

        }

    }

}
