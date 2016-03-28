package easy.list;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * <p/>
 * 234. Palindrome Linked List
 * <p/>
 * Could you do it in O(n) time and O(1) space?
 * <p/>
 * 列表是否是回文列表，
 * n 次数，和一次的空间，
 * <p/>
 * 两个指针，跑一半，但是不知什么时候是一半，就是这个思路
 * <p/>
 * 1 -2 -3 - 4
 * <p/>
 * 1-2-3
 */
public class PalindromeList {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.isPalindrome(ListNodeFactory.buildListNode(new int[]{1})));
    }

    public static class Solution {
        public boolean isPalindrome(ListNode head) {

            if (head == null) return true;

            //找到中点
            int count = 0;
            ListNode n = head;
            while (n != null) { //O(n)
                count++;
                n = n.next;
            }

            int mid = count / 2;

            //将后部分列表反转，然后两个指针进行行动，比较
            //下面的地方都没有耗时 超过O(n)；

            ListNode first = head;
            ListNode second = head;

            ListNode midNode = null;
            int i = 0;
            while (i < mid) {
                second = second.next;
                i++;
            }
            if (count % 2 == 0) {
                //偶数
                midNode = reverseList(second);
            } else {
                //奇数
                midNode = reverseList(second.next);
            }

            // compile
            while (midNode != null) {
                if (first.val != midNode.val) return false;
                midNode = midNode.next;
                first = first.next;
            }


            return true;

        }

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
