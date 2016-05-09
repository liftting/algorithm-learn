package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/3.
 * <p/>
 * 35. Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p/>
 * You may assume no duplicates in the array.
 * <p/>
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * <p/>
 * 1,有序， 查找位置，二分查找
 */
public class SearchInsertPos {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1};
        CommonUtil.show(s.searchInsert(nums, 0));
        CommonUtil.show(s.searchInsert(nums, 2));
    }

    public static class Solution {
        public int searchInsert(int[] nums, int target) {
            if (nums == null || nums.length <= 0) return 0;

            int len = nums.length;
            int left = 0, right = len - 1;

            while (left < right) {
                int mid = (left + right) / 2;
                int val = nums[mid];
                if (val < target) {
                    left = mid + 1;
                } else if (val > target) {
                    right = mid;
                } else {
                    return mid;
                }
            }

            //开始，结尾
            if (right == nums.length - 1) {
                if (target > nums[right]) {
                    return right + 1;
                } else {
                    return right;
                }
            }

            return right;

        }
    }

}
