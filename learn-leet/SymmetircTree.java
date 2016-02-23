import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wm on 16/2/23.
 */
public class SymmetircTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {

        public boolean isSymmetric(TreeNode root) {
            //基础check
            if (root == null) return true;
            //根节点左右check
            if (!check(root.left, root.right)) return false;

            //基于左右节点，递归获取
            List<TreeNode> leftList = new ArrayList<TreeNode>();
            List<TreeNode> rightList = new ArrayList<TreeNode>();

            getLeftNode(root.left, leftList);
            getRightNode(root.right, rightList);

            //节点不对称
            if (leftList.size() != rightList.size()) return false;

            for (int i = 0; i < leftList.size(); i++) {
                TreeNode leftNode = leftList.get(i);
                TreeNode rightNode = rightList.get(i);

                if (!check(leftNode, rightNode)) {
                    return false;
                }
            }
            return true;
        }

        private boolean check(TreeNode leftNode, TreeNode rightNode) {

            if (leftNode == null && rightNode == null) return true;

            if (leftNode != null && rightNode != null && leftNode.val == rightNode.val) return true;

            return false;

        }

        private void getLeftNode(TreeNode node, List<TreeNode> left) {
            left.add(node);
            if (node == null) return;
            getLeftNode(node.left, left);
            getLeftNode(node.right, left);

        }

        private void getRightNode(TreeNode node, List<TreeNode> right) {
            right.add(node);
            if (node == null) return;
            getRightNode(node.right, right);
            getRightNode(node.left, right);

        }


    }

}
