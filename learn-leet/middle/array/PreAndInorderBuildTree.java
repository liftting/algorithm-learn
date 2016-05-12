package middle.array;

import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/5/11.
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p/>
 * 前序(中-左-右)  中序（左-中-右） 数组，来构建这个二叉树
 * <p/>
 * <p/>
 * <p/>
 * 1， 前 的第一个节点，跟节点，  在中序中查找，左边的是左字树，右边是右子树
 * 递归，
 * <p/>
 * 1,
 */
public class PreAndInorderBuildTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNodeFactory.show(s.buildTree(new int[]{1, 2, 4, 5, 3}, new int[]{4, 2, 5, 1, 3}));
    }

    public static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {

            return search(preorder, inorder, 0, 0, inorder.length);
        }

        public TreeNode search(int[] preorder, int[] inorder, int index, int left, int right) {

            if (index >= preorder.length || left > right) return null;

            TreeNode root = new TreeNode(preorder[index]);

            int j = left;
            for (int i = left; i <= right; i++) {
                if (inorder[i] == preorder[index]) {
                    //找到了跟节点
                    j = i;
                    break;
                }
            }

            root.left = search(preorder, inorder, index + 1, left, j - 1); //左边子树根
            root.right = search(preorder, inorder, index + (j - left + 1), j + 1, right); //右边子树根 index +   === (j - left + 1) 左边子树的长度，从中序的计算出来的

            return root;
        }

    }

}
