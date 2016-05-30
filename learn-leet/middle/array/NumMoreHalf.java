package middle.array;

import java.util.Arrays;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/26.
 * 重复数字超过一半
 */
public class NumMoreHalf {


    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.getTwo(new int[]{1, 2, 3, 2, 4, 2, 2, 5, 2, 6, 2, 7, 2}));
    }

    public static class Solution {

        /**
         * 排序，快排，
         * 中间那个直接定位就是， O(nlongn + 1)
         *
         * @param nums
         * @return
         */
        public int getOne(int[] nums) {
            Arrays.sort(nums);

            int len = nums.length;
            return nums[len];

        }

        public int getTwo(int[] nums) {
            int ct = 0;
            int data = -1;
            for (int i = 0; i < nums.length; i++) {
                if (ct == 0) {
                    data = nums[i];
                    ct = 1;
                } else {
                    if (data == nums[i]) {
                        ct++;
                    } else {
                        ct--;
                    }
                }
            }

            return data;
        }

    }

}
