package easy.list;

/**
 * Created by wm on 16/3/25.
 * <p/>
 * <p/>
 * 19. Remove Nth Node From End of List
 * <p/>
 * 删除链表的倒数第n个元素
 * <p/>
 * 两个游标，一个先走n步，一个在头边，然后两个一起走，后走那个就是倒数第几个的游标位置，
 * <p/>
 * <p/>
 * 0   1 2 3 4 5
 * n = 2
 */
public class RemoveNthNode {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.removeNthFromEnd(ListNodeFactory.buildListNode(new int[]{1}), 1));
    }

    public static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || n <= 0) return head;
            //自己创建一个头
            ListNode pre = new ListNode(0);
            pre.next = head;

            ListNode first = pre;
            ListNode second = pre;

            int i = 1;
            while (i++ <= n) {
                first = first.next;
            }

            // 0   1 2 3 4 5
            //  n = 2  first 5 second:3
            while (first.next != null) {
                first = first.next;
                second = second.next;
            }

            // 这里second 指向要删除的前面节点
            second.next = second.next.next;
            return pre.next;
        }
    }

}
