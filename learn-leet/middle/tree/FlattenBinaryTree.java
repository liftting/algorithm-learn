package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/4/5.
 * <p/>
 * 114. Flatten Binary Tree to Linked List
 * Given a binary tree, flatten it to a linked list in-place.
 * 先序遍历
 * <p/>
 * 转换后，都是右边子树
 */
public class FlattenBinaryTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.flatten(TreeNodeFactory.build());

    }

    public static class Solution {
        public void flatten(TreeNode root) {

            if (root == null) return;

            List<TreeNode> dataList = new ArrayList<TreeNode>();
            search(root, dataList);

            TreeNode head = dataList.get(0);
            root = head;
            for (int i = 1; i < dataList.size(); i++) {
                head.right = dataList.get(i);
                head.left = null;
                head = dataList.get(i);
            }

        }

        public void search(TreeNode root, List<TreeNode> list) {

            if (root == null) return;

            list.add(root);

            if (root.left != null) {
                search(root.left, list);
            }

            if (root.right != null) {
                search(root.right, list);
            }

        }
    }

}
