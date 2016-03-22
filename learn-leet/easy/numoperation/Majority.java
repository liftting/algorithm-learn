package easy.numoperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wm on 16/3/15.
 * <p/>
 * 169. easy.numoperation.Majority Element
 */
public class Majority {

    public class Solution {

        //因为是n/2的重复率，所以直接判断
        public int majorityElement(int[] nums) {
            int res = nums[0];
            int count = 1;

            for (int num : nums) {
                if (res == num) ++count;
                else --count;

                if (count == 0) {
                    res = num;
                    count = 1;
                }
            }

            return res;
        }

        public int majorityElement1(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);
            return nums[len / 2];
        }
    }

}
