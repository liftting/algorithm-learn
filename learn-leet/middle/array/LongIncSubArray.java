package middle.array;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/14.
 * 300. Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p/>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * <p/>
 * Your algorithm should run in O(n2) complexity.
 * <p/>
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p/>
 * 一个数组，查找最长递增的子数组长度
 * <p/>
 * <p/>
 * 1，暴力，以每个点都作为起始点，然后向后遍历， 时间复杂度 O(n2)
 * 2，nlogn  -- 不断的划分缩小，二分这个时间复杂度是这个
 * <p/>
 * 贪心
 * <p/>
 * f()里面存储递增的集合，
 * f(i+1) = f(i) + A(i+1); // 判断是否要添加到递增集合，以及要更新这个集合
 * 贪心策略来处理，
 * 贪心是前面计算的状态对后面没有影响，但是这里选择递增时，前面选择的数字是对后面再进行选择会有影响，所以会导致错误的解，
 * <p/>
 * 3，dp
 * 计算好前面的没一项的最优值，存表，后面再进行查询，判断，
 *
 *
 */
public class LongIncSubArray {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18, 18, 19, 102}));
        CommonUtil.show(s.lengthOfLIS(new int[]{2, 2}));
        CommonUtil.show(s.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 2, 10, 3, 4, 5, 6, 7, 8}));
    }

    public static class Solution {
        // 1, 3, 6, 7, 9, 2, 10, 3, 4, 5, 6, 7, 8

        /**
         * 1-1
         * 2-2
         * 3-2
         */

        // 1  2  3  4  5  2  6   3  4  5  6  7  8
        // 下面的解法是利用动态规划的思想，但是时间复杂度比较高， 0(n2)
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length <= 0) return 0;

            //定义一个数组来计算每个位置的最长递增的长度值，
            int[] fn = new int[nums.length];

            fn[0] = 1;
            int curMax = 0;
            int max = 1;

            for (int i = 1; i < nums.length; i++) {
                curMax = 0; //穷举，内循环优化
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j]) {
                        if (fn[j] + 1 > curMax) {
                            curMax = fn[j] + 1;
                        }
                    }
                }

                if (curMax == 0) {
                    fn[i] = 1;
                } else {
                    fn[i] = curMax;
                }

                if (fn[i] > max) {
                    max = fn[i];
                }

            }

            return max;

        }

        public int lengthOfLISI(int[] nums) {
            if (nums == null || nums.length <= 0) return 0;

            //定义一个数组来计算每个位置的最长递增的长度值，
            int[] fn = new int[nums.length];

            fn[0] = 1;
            int max = 1;

            for (int i = 1; i < nums.length; i++) {
                fn[i] = 1;
                // 内村换是需要使用 二分来进行优化的，
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j]) {
                        if (fn[j] + 1 > fn[i]) {
                            fn[i] = fn[j] + 1;
                        }
                    }
                }

                if (fn[i] > max) {
                    max = fn[i];
                }
            }
            return max;
        }


    }

    public static class ErrorSolution {
        //解的是错误的
        public int lengthOfLIS(int[] nums) {

            if (nums == null || nums.length <= 0) return 0;

            int[] inc = new int[nums.length];

            inc[0] = nums[0];
            int inct = 0;

            for (int i = 1; i < nums.length; i++) {
                int cur = nums[i];

                if (cur > inc[inct]) {
                    inc[++inct] = cur;
                } else {
                    for (int j = inct; j >= 0; j--) {
                        if (cur < inc[j]) {
                            //淘汰一个最近的即可
                            inc[j] = cur;
                            break;
                        }
                    }
                }


            }

            return inct + 1;

        }


    }

}
