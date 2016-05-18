package middle.array;

import java.util.Arrays;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/26.
 * 16. 3Sum Closest
 * <p/>
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p/>
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * <p/>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p/>
 * 三个数 最靠近目标
 * <p/>
 * -4  -1  1  2 3  3  4  5    -- 8
 * <p/>
 * -3 0 1 2
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }

    public static class Solution {
        public int threeSumClosest(int[] nums, int target) {

            if (nums == null || nums.length <= 0) return 0;

            // 1,paixu， 2，左边一指针，右边一  mid

            Arrays.sort(nums);

            //一个数为定数
            int value = Integer.MAX_VALUE;

            int result = 0;

            for (int i = 0; i < nums.length; i++) {
                int left = i + 1;
                int right = nums.length - 1;

                int sum = 0;
                while (left < right) {
                    sum = nums[i] + nums[left] + nums[right];

                    int dele = Math.abs(sum - target);

                    if (dele < value) {
                        value = dele;
                        result = sum;
                    }


                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        return sum;
                    }
                }
            }


            return result;


        }
    }

}
