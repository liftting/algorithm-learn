package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.tree.TreeNode;

/**
 * Created by wm on 16/4/29.
 * 94. Binary Tree Inorder Traversal
 *
 * 中需遍历
 *
 */
public class BinaryTreeInOrder {

    public static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<Integer>();

            if (root == null) return result;

            search(root, result);

            return result;

        }


        private void search(TreeNode node, List<Integer> list) {
            if (node == null) return;

            search(node.left, list);
            list.add(node.val);
            search(node.right, list);

        }
    }

}
