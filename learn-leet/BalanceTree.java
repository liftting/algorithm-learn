/**
 * 用来检测树是否是平衡的，
 * 左右高度差不超过1
 */
public class BalanceTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public boolean isBalanced(TreeNode root) {
            return search(root) >= 0;
        }

        public int search(TreeNode node) {
            if (node == null) return 0;

            int left = search(node.left);
            int right = search(node.right);

            //前面已经检测到了不平衡，直接返回-1
            if (left < 0 || right < 0 || Math.abs(left - right) > 1) return -1;

            //到底部
            if (left >= right)
                return left + 1;
            else
                return right + 1;
        }
    }


}
