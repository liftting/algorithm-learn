/**
 * 求 树的深度
 */

import TreeNodeFactory.TreeNode;

public class TreeDepth {

    public static void main(String[] args) {

        TreeNodeFactory.build();

    }

    public class Solution {
        public int maxDepth(TreeNode root) {
            return search(root);
        }

        public int search(TreeNode node) {
            //一个节点，深度为1，
            if (node == null) return 0;
            int left = 1, right = 1; //默认节点不为空，初始化设置为1，

            left += search(node.left); //子树的深度 直接相加
            right += search(node.right);

            return Math.max(left, right);

        }
    }

}
