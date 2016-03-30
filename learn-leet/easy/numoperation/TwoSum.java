package easy.numoperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/29.
 * 1. Two Sum
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p/>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.twoSum(new int[]{3, 2, 4}, 6));
    }

    public static class Solution {
        public int[] twoSumOther(int[] nums, int target) {
            int[] result = new int[2];
            if (nums == null || nums.length == 0 || nums.length <= 1) return result;
            Arrays.sort(nums); //排序后索引位置发生变化了，

            int j = 0;
            while (j < nums.length && nums[j] <= target) {
                j++;
            }

            int i = 0;
            if (j >= nums.length) j = nums.length - 1;
            while (j >= i) {
                if (nums[i] + nums[j] == target) break;
                j--;
            }

            if (j > i) {
                result[0] = i;
                result[1] = j;
                return result;
            }

            return result;

        }

        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }

            // map的数据里面，查找剩余的，得到位置
            for (int i = 0; i < nums.length; i++) {
                int dif = target - nums[i];
                Integer index = map.get(dif);

                if (index != null && i != index) {
                    if (index > i)
                        return new int[]{i, index};
                    else
                        return new int[]{index, i};
                }
            }

            return null;
        }


    }

}
