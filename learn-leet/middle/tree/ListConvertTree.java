package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.list.ListNode;
import easy.list.ListNodeFactory;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/4/12.
 * 109. Convert Sorted List to Binary Search Tree
 * Given a singly linked list where elements are sorted in ascending order
 * 单链表升序排序，转换 二叉树  高度平衡的二叉树   中间元素
 * convert it to a height balanced
 * <p/>
 * <p/>
 * 1，中间元素为跟
 * 2，递归，每次都取中间的元素为节点，那么就能保证是平衡的，
 */
public class ListConvertTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNodeFactory.show(s.sortedListToBST(ListNodeFactory.buildListNode(new int[]{1, 2, 3, 4, 5, 6})));
    }

    public static class Solution {
        public TreeNode sortedListToBST(ListNode head) {

            if (head == null) return null;

            List<TreeNode> data = new ArrayList<TreeNode>();
            while (head != null) {
                data.add(new TreeNode(head.val));
                head = head.next;
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
