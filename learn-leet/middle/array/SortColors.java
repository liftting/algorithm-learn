package middle.array;

import java.util.Collections;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/9.
 * 75. Sort Colors
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p/>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p/>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * <p/>
 * 排序
 * 0 1 2 0 1 2 - 0 0 0 1 1 1 2 2 2
 * 1，
 * 从小到大排序，快排 快速排序
 */
public class SortColors {

    public static void main(String[] args) {
        //
        Solution s = new Solution();
        s.sortColors(new int[]{0, 0, 1, 2, 1, 0, 2, 1});
    }

    public static class Solution {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length <= 0) return;
            quickSort(nums, 0, nums.length - 1);
        }

        public void quickSort(int[] nums, int left, int right) {
            if (left < right) {
                int pos = partition(nums, left, right);
                quickSort(nums, left, pos - 1);
                quickSort(nums, pos + 1, right);
            }
        }

        //划分，以左边为基准
        public int partition(int[] nums, int left, int right) {
            //3 5 2 7 9 1
            int par = nums[left];
            while (left < right) {
                //add
                while (left < right && nums[right] >= par) right--;
                if (left < right) {
                    nums[left] = nums[right];
                }

                while (left < right && nums[left] <= par) left++;

                if (left < right) {
                    nums[right] = nums[left];
                }

            }
            nums[left] = par;
            CommonUtil.show(nums);
            return left;
        }
    }

}
