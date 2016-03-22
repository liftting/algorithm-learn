package easy.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 16/2/23.
 */
public class SymmetircTree {

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


        // === 网上的简洁，递归重新规划树结构 可以

        private TreeNode flipTree(TreeNode root) {
            if (root == null)
                return null;
            // 重新规划数，结构，方便后面的比较，直接将节点的left left对应上
            TreeNode rTree = new TreeNode(root.val);
            rTree.left = flipTree(root.right);
            rTree.right = flipTree(root.left);
            return rTree;
        }

        private boolean compareTree(TreeNode tr1, TreeNode tr2) {
            if (tr1 == null && tr2 == null)
                return true;
            else if ((tr1 != null && tr2 != null) && (tr1.val == tr2.val))
                return compareTree(tr1.left, tr2.left) && compareTree(tr1.right, tr2.right);
            else
                return false;
        }
        //======

    }

}
