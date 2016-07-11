package middle.tree;

import easy.CommonUtil;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/7/11.
 * 230. Kth Smallest Element in a BST
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p/>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p/>
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * <p/>
 * 求the k最小元素 ，在二叉查找树种，
 * <p/>
 * Try to utilize the property of a BST.
 * What if you could modify the BST node's structure? //修改结构
 * The optimal runtime complexity is O(height of BST). o的时间复杂度 高度
 * <p/>
 * <p/>
 * 1
 * 2 3
 * <p/>
 * 解法：
 * 1，中序遍历，得到元素也不是按照顺序排列的，需要在处理，  左子树 < 根 < 右子树，
 * 不断递归 ，左边子树，回退，
 */
public class TheKSmallTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.kthSmallest(TreeNodeFactory.buildBSTTree(),1));
    }

    public static class Solution {

        private int mk;

        public int kthSmallest(TreeNode root, int k) {
            this.mk = k;
            return search(root);
        }

        public int search(TreeNode node) {

            if (node == null) return -1;

            int val = search(node.left);

            //到底部了，开始回退，进行判断，每次回退要减 -1
            if (mk == 0) return val; //需要全局变量，函数调用传递的是值， copy

            if ((--mk) == 0) return node.val;

            return search(node.right);

        }
    }


}
