package easy.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 16/3/23.
 * 涉及到树的深度搜
 * <p/>
 * determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * <p/>
 * <p/>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * <p/>
 * 112. Path Sum
 * <p/>
 * 思路：
 * 在进行树的递归中，因为递归到底部是无法搜集到所有的值集合的，所以，采用集合的方式，
 * 将上次返回的值放到这次的集合数据中来
 * 即将子树递归的总值收集回来， 在加上父亲的值，就是节点的值
 */
public class PathSum {

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = TreeNodeFactory.build();
        System.out.print(s.hasPathSum(root, 8));
    }

    public static class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;
            List<Integer> data = search(root);
            for (Integer i : data) {
//                System.out.println(i);
                if (i == sum) return true;
            }
            return false;
        }

        /**
         * 是否存在路径是，跟到叶子节点，上路径数据和满足条件
         * 使用深度搜索， 使用递归的方式来处理，
         * 使用集合将每个递归节点存储，
         *
         * @param node
         * @return 返回的list存储的是，每次路径的值和
         */
        private List<Integer> search(TreeNode node) {
            List<Integer> list = new ArrayList<Integer>();
            if (node == null) {
                return list;
            }
            if (node.left != null) {
                //递归回退，将子的返回的数据集合添加到，父亲的集合中
                for (int num : search(node.left)) {
                    num += node.val;
                    list.add(num);
                }
            }
            if (node.right != null) {
                for (int num : search(node.right)) {
                    num += node.val;
                    list.add(num);
                }
            }

            if (node.left == null && node.right == null) {
                // 到叶子节点了，算一个值
                list.add(node.val);
            }

            return list;

        }
    }

}
