package easy.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 16/3/22.
 */
public class ListNodeFactory {

    public static ListNode buildListNode() {
        ListNode root = new ListNode(1);
        ListNode next = root;
        for (int i = 2; i < 10; i++) {
            ListNode temp = new ListNode(i);
            next.next = temp;
            next = temp;
        }

//        next.next = root;

        return root;
    }

    public static ListNode buildCycleListNode() {
        ListNode root = new ListNode(1);
//        ListNode next = root;
//        for (int i = 2; i <= 3; i++) {
//            ListNode temp = new ListNode(i);
//            next.next = temp;
//            next = temp;
//        }
//        ListNode four = new ListNode(4);
//        ListNode five = new ListNode(4);
//
//        next.next = four;
//        four.next = five;
//        five.next = next;

        ListNode second = new ListNode(2);
        root.next = second;
        second.next = root;


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

    public static void showListNodeSingle(ListNode node) {

        if (node == null) System.out.print("is empty");

        System.out.print(node.val);
    }

    public static List<List<Integer>> buildTwoList() {
// 1 2
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int[][] data = new int[][]{
                {-1}, {2, 3}, {1, -1, 3}

        };
        for (int i = 0; i < data.length; i++) {
            int[] res = data[i];
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < res.length; j++) {
                list.add(res[j]);
            }
            result.add(list);
        }

        return result;

    }

}
