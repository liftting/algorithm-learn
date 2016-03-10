import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by wm on 16/3/10.
 */
public class BinaryTreeOrderOne {

    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer x) {
            val = x;
        }
    }

    /**
     * 1，跟节点入队列，
     * 2，不断从队列头获取节点，如果左右节点不为空，入队列，
     * 3,下面的遍历是层级的遍历，
     */
    public class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> orderList = new ArrayList<List<Integer>>();
            if (root == null) return orderList;

            Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.add(root);

            List<Integer> list = new ArrayList<Integer>();
            orderList.add(list);

            int size = 1;

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                size--;


                if (node == null) continue;

                list.add(node.val);

                if (size == 0 && (node.left != null || node.right != null)) {
                    list = new ArrayList<Integer>();
                    orderList.add(list);
                }


                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                }

            }

            return orderList;

        }

    }

    public class RevertSolution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> orderList = new ArrayList<List<Integer>>();
            if (root == null) return orderList;

            Stack<List<Integer>> stack = new Stack<List<Integer>>();

            List<TreeNode> oldList = new ArrayList<TreeNode>();
            oldList.add(root);

            while (true) {
                //两个集合，不断更新
                List<TreeNode> dataList = new ArrayList<TreeNode>();
                List<Integer> list = new ArrayList<Integer>();

                for (TreeNode node : oldList) {
                    list.add(node.val);

                    if (node.left != null) {
                        dataList.add(node.left);
                    }
                    if (node.right != null) {
                        dataList.add(node.right);
                    }

                }

                if (list.size() > 0) {
                    stack.add(list);
                }

                if (dataList.size() == 0) break;

                oldList = dataList;


            }

            while (!stack.empty()) {
                orderList.add(stack.pop());
            }

            return orderList;
        }
    }


    public class OtherSolution {
        /**
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> orderList = new ArrayList<List<Integer>>();
            if (root == null) return orderList;

            List<TreeNode> oldList = new ArrayList<TreeNode>();
            oldList.add(root);

            while (true) {
                //两个集合，不断更新
                List<TreeNode> dataList = new ArrayList<TreeNode>();
                List<Integer> list = new ArrayList<Integer>();

                for (TreeNode node : oldList) {
                    list.add(node.val);

                    if (node.left != null) {
                        dataList.add(node.left);
                    }
                    if (node.right != null) {
                        dataList.add(node.right);
                    }

                }

                if (list.size() > 0) {
                    orderList.add(list);
                }

                if (dataList.size() == 0) break;

                oldList = dataList;


            }

            return orderList;

        }
    }


}
