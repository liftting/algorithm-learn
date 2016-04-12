package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.list.ListNodeFactory;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/4/12.
 * 108. Convert Sorted Array to Binary Search Tree
 * 数组转换类似，ListConvertTree
 */
public class ArrayConertTree {


    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNodeFactory.show(s.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6}));
    }

    public static class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {

            if (nums == null || nums.length <= 0) return null;

            List<TreeNode> data = new ArrayList<TreeNode>();
            for (int i = 0; i < nums.length; i++) {
                data.add(new TreeNode(nums[i]));
            }

            //每次构建时，都取已经排序的中间元素
            return build(data, 0, data.size() - 1);

        }

        public TreeNode build(List<TreeNode> lis, int begin, int end) {
            int mid = (begin + end) / 2;

            TreeNode node = lis.get(mid);
            if (begin <= mid - 1) {
                node.left = build(lis, begin, mid - 1);
            }

            if (end >= mid + 1) {
                node.right = build(lis, mid + 1, end);
            }

            return node;

        }
    }

}
