package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/12.
 * 209. Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p/>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p/>
 * click to show more practice.
 * <p/>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * <p/>
 * 一个数组，求子数组的最小长度，值>=s
 * 数据都是正数，所以不用考虑负数时，相加失败策略，
 * <p/>
 * 解法：
 * 1，暴力遍历，双重循环，计算出所有的子集合
 * 2，两个指针， 小于就向前试探，大于就缩小 ()  双指针问题，
 */
public class MinSubarraySum {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.minSubArrayLen(10, new int[]{2, 3, 5, 2, 3, 4}));
    }

    public static class Solution {
        //暴力遍历，时间复杂度 o(n2)
        public int minSubArrayLen(int s, int[] nums) {
            int sum = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < nums.length; i++) {
                sum = 0;

                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];

                    if (sum >= s) {
                        if ((j - i + 1) < min) {
                            min = (j - i + 1);
                            System.out.print("[" + i + "," + j + "]");
                        }
                    }
                }
            }

            if (min == Integer.MAX_VALUE) min = 0;

            return min;

        }
    }

    public static class OtherSolution {
        public int minSubArrayLen(int s, int[] nums) {

            int i = 0, j = 0;
            int sum = 0;
            int min = Integer.MAX_VALUE;

            while (i < nums.length && j < nums.length) {

                while (sum < s && j < nums.length) {
                    sum += nums[j++];
                }

                while (sum >= s && i <= j) {
                    min = Math.min(min, j - i);
                    sum -= nums[i++];
                }

            }

            return min == Integer.MAX_VALUE ? 0 : min;

        }
    }

}
