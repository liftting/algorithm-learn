package middle.list;

import easy.CommonUtil;
import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/4/8.
 * 141	Linked List Cycle
 * Given a linked list, determine if it has a cycle in it.
 * <p/>
 * 12345
 * 2  - 3
 * 3 - 5
 * 4 - 2
 * 5 - 4
 * 1 -  1
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.hasCycle(ListNodeFactory.buildListNode()));
    }

    public static class Solution {
        // 链表有环， 两个指针， 走一步，走两步
        public boolean hasCycle(ListNode head) {
            if (head == null) return false;

            ListNode one = head;
            if (head.next == null) return false;
            ListNode second = head.next.next;


            while (one != null && second != null) {
                if (one == second) return true;

                one = one.next;
                if (second.next == null) return false;

                second = second.next.next;

            }

            return false;

        }
    }

}
