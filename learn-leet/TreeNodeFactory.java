import java.util.List;

/**
 * 统一树型结构，构造器
 */
public class TreeNodeFactory {

    public static TreeNode build() {
        TreeNode root = new TreeNode(1);

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        node2.left = node5;
        node2.right = node6;


        return root;

    }

    public static void show(TreeNode node) {
        BinaryTreeOrderOne.OtherSolution solution = new BinaryTreeOrderOne.OtherSolution();
        List<List<Integer>> datalist = solution.levelOrder(node);

        for (List<Integer> data : datalist) {
            for (Integer t : data) {
                System.out.print(t + " ");
            }

            System.out.print("\n");

        }

    }

    public static ListNode buildListNode() {
        ListNode root = new ListNode(0);
        ListNode next = root;
        for (int i = 1; i < 1; i++) {
            ListNode temp = new ListNode(i);
            next.next = temp;
            next = temp;
        }

        return root;
    }


}
