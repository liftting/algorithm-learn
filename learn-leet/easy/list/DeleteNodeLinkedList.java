package easy.list;

/**
 * Created by wm on 16/3/14.
 * 237 ：delete node in linkedlist
 */
public class DeleteNodeLinkedList {


    public class Solution {
        public void deleteNode(ListNode node) {
            if (node != null && node.next != null) {
                node.val = node.next.val;
                node.next = node.next.next;
            }

        }
    }

}
