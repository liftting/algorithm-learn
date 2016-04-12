package middle.numoperation;

import java.util.Arrays;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/12.
 * 268. Missing Number
 * <p/>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p/>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * <p/>
 * 线性，不使用空间
 * <p/>
 * [1] -- 0
 * [0] -- 1
 * [1,0] -- 2
 */
public class MissingNumber {

    public static void main(String[] args) {
//        Solution s = new Solution();
//        CommonUtil.show(s.missingNumber(new int[]{1, 0}));
        missingNumber(new int[]{0, 1, 3, 4, 5, 6, 7});
    }

    public static class Solution {
        public int missingNumber(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            Arrays.sort(nums);

            int pre = nums[0];
            if (pre > 0) return 0;

            int i = 0;
            for (i = 1; i < nums.length; i++) {
                if (nums[i] - pre > 1) return pre + 1;
                pre = nums[i];

            }

            if (i == nums.length) return nums[i - 1] + 1;

            return -1;
        }


    }

    // code from net
    // 因为是0 -n 那么可以根据索引来进行判断，算总和 缺少的
    // 0 1 3 4 5 6 -- 索引都缺少了
    public static int missingNumber(int[] nums) {
        int sum = 0, i = 0;

        for (; i < nums.length; i++)
            sum = sum + i - nums[i];

        return (sum + i);
    }

}
