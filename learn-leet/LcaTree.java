/**
 * Created by wm on 16/3/16.
 * <p/>
 * 235. Lowest Common Ancestor of a Binary Search Tree
 */
public class LcaTree {


    public static class Solution {
        /**
         * 思路： 这个树是二叉树， 符合 左右规则的
         * 5
         * 3   6
         * 2  4
         * 1
         *
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null) return null;

            //都在左边
            if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestor(root.left, p, q);
            } else
                //都在右边
                if (p.val > root.val && q.val > root.val) {
                    return lowestCommonAncestor(root.right, p, q);
                }
            //一左 一右  或者一个节点有相等的，也是直接返回父节点
            return root;
        }

    }

}
