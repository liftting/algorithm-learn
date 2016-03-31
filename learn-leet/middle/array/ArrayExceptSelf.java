package middle.array;

import java.util.HashMap;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/31.
 * 238. Product of Array Except Self
 * <p/>
 * Given an array of n integers where n > 1, nums,
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p/>
 * Solve it without division and in O(n).
 * <p/>
 * For example, given [1,2,3,4], return [24,12,8,6].
 * <p/>
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 * 常数空间，
 * 计算除自己外的其他数字的乘积
 */
public class ArrayExceptSelf {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.productExceptSelf(new int[]{1, 1, 2, 3, 4}));
    }

    public static class Solution {
        // 包含0情况  0 1 2 0 1
        public int[] productExceptSelf(int[] nums) {
            //所有数像乘，再除，
            if (nums == null || nums.length == 0) return null;

            int[] result = new int[nums.length];

            long sum = 1;
            Integer zero = null;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    if (zero == null) {
                        zero = i;
                    } else {
                        //因为有两个，指定都是0
                        return result;
                    }
                } else {
                    sum *= nums[i];
                }
            }

            if (zero != null) {
                result[zero] = (int) sum;
                return result;
            }

            for (int j = 0; j < nums.length; j++) {
                result[j] = (int) (sum / nums[j]);
            }
            return result;
        }
    }

}
