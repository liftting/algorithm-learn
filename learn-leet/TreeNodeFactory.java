/**
 * 统一树型结构，构造器
 */
public class TreeNodeFactory {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode build() {
        TreeNode root = new TreeNode(1);

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);

        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);

        node1.left = node3;
        node3.left = node4;

        return root;

    }

}
