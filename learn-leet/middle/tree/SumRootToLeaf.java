package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/4/24.
 * <p/>
 * 129. Sum Root to Leaf Numbers
 */
public class SumRootToLeaf {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.sumNumbers(TreeNodeFactory.build());
    }

    public static class Solution {
        public int sumNumbers(TreeNode root) {

            if (root == null) return 0;

            List<String> data = search(root);

            int sum = 0;
            for (String val : data) {
//                CommonUtil.show(val);
                sum += Integer.parseInt(val);
            }

            return sum;

        }

        /**
         * 这段代码是，树的遍历，从根到子节点的查询路径打印
         *
         * @param treeNode
         * @return
         */
        public List<String> search(TreeNode treeNode) {
            List<String> list = new ArrayList<String>();
            if (treeNode.left == null && treeNode.right == null) {
                list.add(String.valueOf(treeNode.val));
                return list;
            }

            if (treeNode.left != null) {
                for (String val : search(treeNode.left)) {
                    list.add(treeNode.val + val);
                }
            }
            if (treeNode.right != null) {
                for (String val : search(treeNode.right)) {
                    list.add(treeNode.val + val);
                }
            }

            return list;


        }

    }

    // from net
    public static class GoodSolution {
        public int sumNumbers(TreeNode root) {
            int sum = 0;
            if (root != null)
                sum = sumNumbers(root, 0);
            return sum;
        }

        public int sumNumbers(TreeNode root, int number) {
            if (root == null)
                return 0;
            if (root.left == null && root.right == null) // leaf node
                return number * 10 + root.val;
            else
                // DFS sum of first route + sum of second route
                return sumNumbers(root.left, number * 10 + root.val) + sumNumbers(root.right, number * 10 + root.val);
        }
    }

}
