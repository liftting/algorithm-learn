package middle.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import easy.tree.TreeNode;

/**
 * Created by wm on 16/4/27.
 * 144. Binary Tree Preorder Traversal
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p/>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,2,3].
 */
public class BinaryTreePreOrder {

    public static void main(String[] args) {

    }

    public static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<Integer>();

            if (root == null) return result;

            search(root, result);

            return result;

        }


        private void search(TreeNode node, List<Integer> list) {
            if (node == null) return;

            list.add(node.val);

            search(node.left, list);
            search(node.right, list);

        }
    }

    public class OtherSolution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<Integer>();
            if (root == null)
                return list;

            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);

                if (node.right != null)
                    stack.push(node.right);

                if (node.left != null)
                    stack.push(node.left);
            }

            return list;
        }
    }

}



