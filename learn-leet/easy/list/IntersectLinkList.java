package easy.list;

/**
 * Created by wm on 16/4/8.
 * <p/>
 * <p/>
 * 160. Intersection of Two Linked Lists
 * 长度
 * x
 * y
 * 先走x -y 步
 * <p/>
 * 1 2 3            11 12 13
 * 4 5 6 7  8  9
 */
public class IntersectLinkList {

    public static void main(String[] args) {

        ListNode o1 = new ListNode(3);

        ListNode o2 = new ListNode(2);

        o2.next = o1;

        Solution s = new Solution();
        s.getIntersectionNode(o1, o2);

    }

    public static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

            if (headA == null || headB == null) return null;

            ListNode x = headA;
            ListNode y = headB;

            int xlen = 0;
            int ylen = 0;
            while (x != null) {
                xlen++;
                x = x.next;
            }

            while (y != null) {
                ylen++;
                y = y.next;
            }

            ListNode one = headA;
            ListNode second = headB;


            if (xlen > ylen) {
                one = go(one, xlen - ylen);
            } else if (xlen < ylen) {
                second = go(second, ylen - xlen);
            }

            while (one != null && second != null) {
                if (one == second) return one;
                one = one.next;
                second = second.next;
            }

            return null;


        }

        private ListNode go(ListNode root, int len) {
            int i = 1;
            while (i <= len && root != null) {
                i++;
                root = root.next;
            }

            return root;
        }
    }
}
