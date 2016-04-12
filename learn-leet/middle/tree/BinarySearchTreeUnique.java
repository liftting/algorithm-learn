package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;
import easy.tree.TreeNode;

/**
 * Created by wm on 16/4/11.
 * <p/>
 * 96. Unique Binary Search Trees
 * Given n = 3, there are a total of 5 unique BST's.
 * <p/>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * 231
 * 123
 * 213
 * 132
 * 321
 * <p/>
 * <p/>
 * 构造出多少个搜索树，  左边 比右边小，
 * <p/>
 * 构造多少个后续遍历，逆波兰表达式， -- 错误
 * <p/>
 * 动态规划，问题
 * <p/>
 * <p/>
 * 递归解法：
 * 本题其实关键是递推过程的分析，n个点中每个点都可以作为root，
 * 当 i 作为root时，小于 i  的点都只能放在其左子树中，大于 i 的点只能放在右子树中，此时只需求出左、右子树各有多少种，二者相乘即为以 i 作为root时BST的总数。
 */
public class BinarySearchTreeUnique {

    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 5;
//        CommonUtil.show(s.dpSearch(n));
//        CommonUtil.show(s.numTrees(n));

        OtherSolution os = new OtherSolution();
        CommonUtil.show(os.search(1, n).size());

    }

    public static class Solution {

        private List<List<Integer>> map = new ArrayList<List<Integer>>();

        public int numTrees(int n) {
            search(1, n);
            int cnt = 0;
            if (n <= 2) return n;
            for (List<Integer> list : map) {
                if (list != null && list.size() > 0) {
                    cnt++;
                }
            }
            return cnt;
        }

        //递归查找是会超时的 ， n =19  这里面会有重复问题
        public List<Integer> search(int begin, int end) {
            List<Integer> result = new ArrayList<Integer>();
            if (begin == end) {
                //到节点了
                result.add(begin);
                return result;
            }

            for (int i = begin; i <= end; i++) {
                List<Integer> left = null;
                if (i != begin) {
                    left = search(begin, i - 1);
                }
                List<Integer> right = null;
                if (i != end) {
                    right = search(i + 1, end);
                }

                result.addAll(left);
                result.addAll(right);
            }

            return result;

        }

        // =====  下面的code from 网上的
        // 动态规划解决方案 ，上面操作会出现超时，而且上面也有重复出现
        public int dpSearch(int n) {
            if (n < 0)
                throw new IllegalArgumentException();
            int[] total = new int[n + 1];
            total[0] = total[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int k = 0; k < i; k++)
                    total[i] += total[k] * total[i - 1 - k];
            }
            return total[n];

        }


    }


    public static class OtherSolution {

        public List<TreeNode> search(int begin, int end) {
            List<TreeNode> result = new ArrayList<TreeNode>();
            if (begin == end) {
                //到节点了
                result.add(new TreeNode(begin));
                return result;
            }

            for (int i = begin; i <= end; i++) {
                List<TreeNode> left = null;
                if (i != begin) {
                    left = search(begin, i - 1);
                }
                List<TreeNode> right = null;
                if (i != end) {
                    right = search(i + 1, end);
                }

                if (left == null) {
                    //没有构建出左边子树  各种情况下的节点都递归出来了，
                    for (TreeNode node : right) {
                        TreeNode root = new TreeNode(i);
                        root.right = node;
                        result.add(root);
                    }
                } else if (right == null) {
                    for (TreeNode node : left) {
                        TreeNode root = new TreeNode(i);
                        root.left = node;
                        result.add(root);
                    }
                } else {
                    //左右都有
                    for (TreeNode n1 : left) {
                        for (TreeNode n2 : right) {
                            TreeNode root = new TreeNode(i);
                            root.left = n1;
                            root.right = n2;
                            result.add(root);
                        }
                    }
                }

            }
            return result;
        }

    }

}
