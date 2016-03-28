package easy.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * 257. Binary Tree Paths
 * <p/>
 * 递归打印  参考 pathSum
 */
public class ShowBinaryTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.binaryTreePaths(TreeNodeFactory.build()));
    }

    public static class Solution {


        public List<String> binaryTreePaths(TreeNode root) {
            List<String> list = new ArrayList<String>();
            if (root == null) return list;

            list.add("");
            return traverse(root, list);
        }

        /**
         * 先序遍历，
         *
         * @param root
         * @param list
         * @return
         */
        private List<String> traverse(TreeNode root, List<String> list) {
            //构造新集合，存储一个字段，递归时，
            List<String> clist = new ArrayList<String>();

            for (String item : list) {
                if ("".equals(item))
                    item = root.val + "";
                else
                    item += "->" + root.val;

                clist.add(item);
            }

            if (root.left == null && root.right == null) {
                return clist;
            }

            List<String> dataList = new ArrayList<String>();
            if (root.left != null) {
                dataList.addAll(traverse(root.left, clist));
            }
            if (root.right != null) {
                dataList.addAll(traverse(root.right, clist));
            }

            return dataList;

        }

    }


}
