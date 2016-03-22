package easy.list;

/**
 * Created by wm on 16/3/17.
 * <p/>
 * Odd Even
 * 328. Odd Even Linked List
 * <p/>
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 * [2,1,4,3,6,5,7,8]
 * [2,4,6,7,1,3,5,8]
 * <p/>
 * The program should run in O(1) space complexity and O(nodes) time complexity.
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode root = ListNodeFactory.buildListNode(new int[]{2, 1, 4, 3, 6, 5, 7, 8});

        Solution s = new Solution();
        ListNode node = s.oddEvenList(root);

        ListNodeFactory.showListNode(node);
    }

    public static class Solution {
        /**
         * 题目意思是每隔两个数取一个值，
         * <p/>
         * 记住三个节点来进行操作，每次， 将第三个节点和第二个交换即可
         *
         * @param head
         * @return
         */
        public ListNode oddEvenList(ListNode head) {

            if (head == null) return head;

            ListNode pre = head;
            ListNode next = pre.next;

            while (next != null) {

                ListNode temp = next.next;

                if (temp != null) {
                    addNode(pre, deleteNode(next, temp));
                }
                // 这个方案可以提供，将偶数 奇数交换处理

                pre = pre.next;
                next = next.next;
            }

            return head;

        }

        /**
         * 删除某节点， 要传其前节点
         *
         * @param preNode
         * @param deleteNode
         * @return
         */
        private ListNode deleteNode(ListNode preNode, ListNode deleteNode) {
            ListNode nextNode = deleteNode.next;
            preNode.next = nextNode;
            return deleteNode;
        }

        /**
         * 在某节点后面添加节点
         *
         * @param preNode
         * @param newNode
         */
        private void addNode(ListNode preNode, ListNode newNode) {
            ListNode temp = preNode.next;
            preNode.next = newNode;
            newNode.next = temp;
        }
    }


    // ==== 别人的方案：简洁
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head;
            ListNode even = head.next;
            ListNode p = even;

            while (odd.next != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = p;
        }
        return head;
    }

}
