package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/1.
 * 153. Find Minimum in Rotated Sorted Array
 * <p/>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p/>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p/>
 * Find the minimum element.
 * <p/>
 * 之前排序的，然后基于一个点旋转， 再找到最小的
 */
public class FindMinArray {

    public static void main(String[] args) {
        Solution s = new Solution();
//        s.findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        CommonUtil.show(s.findMin(new int[]{4, 2}));
    }

    public static class Solution {
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) return -1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] - nums[i - 1] < 0) return nums[i];
            }

            return nums[0];

        }

        // 二分，减少时间复杂度
        public int otherFindMin(int[] num) {
            if (num.length == 0)
                return num[0];

            int left = 0;
            int right = num.length - 1;
            int min = Integer.MAX_VALUE;

            while (left < right) {
                int mid = (left + right) / 2;
                if (left == mid)
                    break;

                // 3 4 5 6 2, left part is equal or ascending, so for this part num[left] is the smallest
                if (num[left] <= num[mid]) {
                    if (num[left] < min)
                        min = num[left];
                    left = mid + 1;
                }
                // 5 6 1 2 3, both min and max value are in left part
                else {
                    if (num[mid] < min)
                        min = num[mid];
                    right = mid - 1;
                }
            }

            if (num[right] < min)
                min = num[right];
            if (num[left] < min)
                min = num[left];

            return min;
        }
    }

}
