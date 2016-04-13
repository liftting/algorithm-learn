package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/13.
 * 152. Maximum Product Subarray
 * <p/>
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * <p/>
 * 求子数组的乘积最大值，
 * <p/>
 * <p/>
 * 因为是连续的，所以可以从最开始就进行计算判断，dp
 */
public class MaxProductArray {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.maxProduct(new int[]{2, -3, -2, 4, -3}));
    }

    public static class Solution {
        public int maxProduct(int[] nums) {

            int sum = Integer.MIN_VALUE;
            int postMax = 1;
            int negMax = 1;
            for (int i = 0; i < nums.length; i++) {
                int cur = nums[i];

                int curPost = postMax * cur;
                int curNeg = negMax * cur;

                postMax = Math.max(curPost, curNeg); // 有可能两个数相乘得正的
                postMax = Math.max(postMax, cur);


                negMax = Math.min(curPost, curNeg); // 记住最小的负数，因为有可能下次相乘出现大的数
                negMax = Math.min(negMax, cur);

                sum = Math.max(postMax, sum);

            }

            return sum;
        }
    }

}
