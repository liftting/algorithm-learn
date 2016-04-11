package middle.list;

import easy.list.ListNode;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/4/7.
 * 2. Add Two Numbers
 * <p/>
 * 两个链表数据相加， 进位
 * 只要考虑 n+1的进位即可
 */
public class AddListNodeNumber {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNodeFactory.showListNode(s.addTwoNumbers(ListNodeFactory.buildListNode(new int[]{9, 8}), ListNodeFactory.buildListNode(new int[]{1})));
    }

    public static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            ListNode cHead = l1;

            ListNode one = l1;
            ListNode second = l2;
            ListNode onePre = l1;

            boolean isGo = false;
            int sum = 0;
            while (one != null && second != null) {
                sum = one.val + second.val;
                if (isGo) sum += 1;
                one.val = sum % 10;
                if (sum / 10 > 0) {
                    isGo = true;
                } else {
                    isGo = false;
                }
                onePre = one;

                one = one.next;
                second = second.next;
            }

            // 1  9  9
            if (one != null) {
                //l2 短  9 9 9 ,1
                cal(onePre, one, isGo);

            } else if (second != null) {
                onePre.next = second;
                cal(onePre, second, isGo);

            } else {
                if (isGo) {
                    onePre.next = new ListNode(1);
                }
            }
            return cHead;
        }

        private void cal(ListNode onePre, ListNode second, boolean isGo) {
            int total = 0;
            while (second != null) {
                total = second.val;
                if (isGo) {
                    total += 1;
                }

                if (total / 10 > 0) {
                    isGo = true;
                } else {
                    isGo = false;
                }

                second.val = total % 10;
                onePre = second;
                second = second.next;
            }

            if (isGo) {
                onePre.next = new ListNode(1);
            }
        }
    }

}
