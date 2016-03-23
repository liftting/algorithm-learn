package easy.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/23.
 * 111	Minimum Depth of Binary Tree
 * 求树的层级
 */
public class MinTreeDepth {

    public static void main(String[] args) {
        new Solution().search(TreeNodeFactory.build());
    }

    public static class Solution {

        //广度优先搜索，
        public void search(TreeNode node) {
            //光度优先搜索
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(node);
            int ct = 0;
            while (!queue.isEmpty()) {
                TreeNode temp = queue.poll();

                System.out.print(temp.val);

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }

        /**
         * 广度优先遍历
         *
         * @param root
         * @return
         */
        public int minDepth(TreeNode root) {
            if (null == root)
                return 0;

            List<TreeNode> list = new ArrayList<TreeNode>();
            list.add(root);

            int depth = 1;
            while (true) {
                //每次将一个层级的节点都放到一个集合中，进行遍历，
                //todo 广度搜索
                List<TreeNode> newList = new ArrayList<TreeNode>();
                for (TreeNode node : list) {
                    if (node.left == null && node.right == null)
                        return depth;

                    if (null != node.left)
                        newList.add(node.left);
                    if (null != node.right)
                        newList.add(node.right);
                }

                list = newList;
                depth++;
            }
        }
    }

}
