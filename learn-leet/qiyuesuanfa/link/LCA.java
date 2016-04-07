package qiyuesuanfa.link;

import easy.list.ListNode;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/4/7.
 * 最近公共祖先LCA Tarjan算法
 * 1,递归
 * 2，策略 ， m+n 一个链表先自动空转
 * 1
 * 25
 * 34  6
 */
public class LCA {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);

        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.left = node3;
        node1.right = node4;

        TreeNode node6 = new TreeNode(6);

        node2.right = node6;


        TreeNodeFactory.show(getLca(root, node1, node2));
    }

    /**
     * 递归的解法，
     *
     * @param root
     * @param one
     * @param second
     * @return
     */
    public static TreeNode getLca(TreeNode root, TreeNode one, TreeNode second) {
        if (root == null) return null;

        if (root == one || root == second) return root;// 注意这里的返回值

        TreeNode left = getLca(root.left, one, second);
        TreeNode right = getLca(root.right, one, second);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }

        return null;

    }

}
