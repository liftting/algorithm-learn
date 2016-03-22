package easy.tree;

/**
 * Created by wm on 16/3/11.
 * <p/>
 * 226. Invert Binary Tree
 * 树镜像转换
 */

public class InvertBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode root = TreeNodeFactory.build();
        TreeNodeFactory.show(root);

        TreeNode invertNode = solution.invertTree(root);
        TreeNodeFactory.show(invertNode);

    }

    public static class Solution {
        /**
         * 1
         * 2                  3
         * 4       5         6        7
         * 8    9  10  11    12   13  14  15
         * <p/>
         * 所有的左边节点，变成右边节点
         *
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {

            root = invert(root);
            return root;

        }

        public TreeNode invert(TreeNode node) {

            if (node == null) return null;

            //递归到了底部，直接返回
            if (node.left == null && node.right == null) return node;

            //放这里是先进行操作，再进行递归

            invertTree(node.left);
            invertTree(node.right);

            //上面是先递归到底部，再进行操作，
            convertLeftRight(node);

            return node;

        }

        public TreeNode convertLeftRight(TreeNode node) {
            if (node == null) return null;

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            return node;
        }

    }

}
