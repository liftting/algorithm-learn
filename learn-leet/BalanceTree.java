/**
 * 用来检测树是否是平衡的，
 * 左右高度差不超过1
 */
public class BalanceTree {

    public class Solution {
        public boolean isBalanced(TreeNode root) {
            return search(root) >= 0;
        }

        public int search(TreeNode node) {
            if (node == null) return 0;

            int left = search(node.left);
            int right = search(node.right);

            //前面已经检测到了不平衡，直接返回-1
            // 这种是如果到跟部检测到不平衡，还要递归回去，直到跟节点
            //可以抛出异常，直接退出的方式，不用递归了，
            if (left < 0 || right < 0 || Math.abs(left - right) > 1) return -1;

            //到底部
            if (left >= right)
                return left + 1;
            else
                return right + 1;
        }
    }


}
