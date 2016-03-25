package easy.list;

/**
 * Created by wm on 16/3/22.
 */
public class ListNodeFactory {

    public static ListNode buildListNode() {
        ListNode root = new ListNode(1);
//        ListNode next = root;
//        for (int i = 2; i < 10; i++) {
//            ListNode temp = new ListNode(i);
//            next.next = temp;
//            next = temp;
//        }

        return root;
    }

    public static ListNode buildListNode(int[] array) {

        if (array == null || array.length <= 0) return new ListNode(0);

        ListNode root = new ListNode(array[0]);
        ListNode next = root;
        for (int i = 1; i < array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            next.next = temp;
            next = temp;
        }

        return root;
    }

    public static void showListNode(ListNode node) {

        if (node == null) System.out.print("is empty");

        while (node != null) {
            System.out.print(node.val + " ");

            node = node.next;
        }
    }

}
