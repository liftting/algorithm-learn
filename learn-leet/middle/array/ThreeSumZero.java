package middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/31.
 * 15. 3Sum
 * <p/>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p/>
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * <p/>
 * 三个数相加是 0
 */
public class ThreeSumZero {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.threeSum(new int[]{7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6});
    }

    public static class Solution {

        /**
         * S = {-1 0 1 2 -1 -4},
         * (-1, 0, 1)
         * (-1, -1, 2)
         * <p/>
         * <p/>
         * -4 -1 -1 0 1 2
         * 查找三个数， paixu
         * 比0小 ，比0 大，
         * <p/>
         * i left right
         * left 从左， right 从右， i 递增
         * 参考网上的，code
         *
         * @param nums
         * @return
         */
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 3) return result;

            Arrays.sort(nums);

            int len = nums.length;
            int left = 0;
            int right = len - 1;
            int sum = 0;
            // -4 -1 -1 0 1 2
            for (int i = 0; i < len - 2; i++) {

                if (i != 0 && nums[i] == nums[i - 1])
                    continue;

                left = i + 1; // 因为i已经作为标准了，所以left向前 添加即可  这要能避免耗时处理
                right = len - 1;

                while (left < right) {
                    if (left > i + 1 && nums[left] == nums[left - 1]) {
                        left++;
                        continue;
                    }
                    if (right < nums.length - 2 && nums[right] == nums[right + 1]) {
                        right--;
                        continue;
                    }
                    sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        List<Integer> item = new ArrayList<Integer>();
                        item.add(nums[i]);
                        item.add(nums[left]);
                        item.add(nums[right]);
                        result.add(item);

                        CommonUtil.showInteger(item);

                        left++;
                        right--;
                    } else if (sum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }

            }

            return result;
        }

    }
}
