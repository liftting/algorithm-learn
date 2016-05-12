package middle.array;

import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/5/12.
 * <p/>
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * <p/>
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p/>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p/>
 * 中序  后序 构造树
 * 中4, 2, 5, 1, 3  后：4，5，2，3，1
 * 1，  后序最后一个节点是跟，
 * <p/>
 * 后序遍历为 gbdehfca，中序遍历为 dgbaechf
 * 后序遍历中的最后一个元素是根节点，a，然后查找中序中a的位置
 * 把中序遍历分成 dgb a echf，而因为节点个数要对应
 * 后序遍历分为 gbd ehfc a，gbd为左子树，ehfc为右子树，
 * <p/>
 * 1,取最后一个元素，谁的，
 */
public class InAndPostorderBuildTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNodeFactory.show(s.buildTree(new int[]{1, 2, 3, 4}, new int[]{1, 3, 4, 2}));
    }

    public static class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return search(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        }

        public TreeNode search(int[] inorder, int[] postorder, int inleft, int inright, int postleft, int postright) {

            if (postright < 0) return null;

            TreeNode root = new TreeNode(postorder[postright]);

            int j = inleft;
            for (int i = inleft; i <= inright; i++) {
                if (inorder[i] == postorder[postright]) {
                    //找到了跟节点
                    j = i;
                    break;
                }
            }

            int leftLen = j - inleft;

            if (j > inleft) {
                root.left = search(inorder, postorder, inleft, j - 1, postleft, postleft + leftLen - 1);
            }
            if (j < inright) {
                root.right = search(inorder, postorder, j + 1, inright, postleft + leftLen, postright - 1);
            }

            return root;
        }
    }

}
