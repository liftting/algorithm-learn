package easy.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/29.
 * 189. Rotate Array
 * <p/>
 * Rotate an array of n elements to the right by k steps.
 * <p/>
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * <p/>
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * <p/>
 * 倒数k 位置，
 * <p/>
 * 1,2   3  == 2,1
 */
public class RotateArray {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.rotate(new int[]{1, 2}, 3));
    }

    public static class Solution {
        //
        public int[] rotate(int[] nums, int k) {
            if (k <= 0) return nums;
            //后移k 位，
            if (nums == null || nums.length == 0) return nums;

            k = k % nums.length;
            if (k == 0) return nums;

            int[] last = new int[k];

            for (int i = nums.length - k, j = 0; i < nums.length; i++) {
                last[j++] = nums[i];
            }

            //
            int i = nums.length - k - 1;
            int j = nums.length - 1;

            while (i >= 0) {
                nums[j--] = nums[i--];
            }

            for (int ci = 0; ci < k; ci++) {
                nums[ci] = last[ci];
            }

            return nums;
        }
    }

}
