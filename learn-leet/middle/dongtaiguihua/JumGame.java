package middle.dongtaiguihua;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/9.
 * <p/>
 * 55. Jump Game
 * <p/>
 * For example:
 * A = [2,3,1,1,4], return true.
 * <p/>
 * A = [3,2,1,0,4], return false.
 * <p/>
 * 存储的都是能够跳转的最大范围，问是否能够跳转到最后一个位置，
 */
public class JumGame {


    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    //特别大的数时，会超时
    public static class Solution {
        public boolean canJumpNo(int[] nums) {
            if (nums == null || nums.length <= 0) return false;

            boolean[] flag = new boolean[nums.length];

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    for (int j = 1; j <= nums[i]; j++) {
                        if (i + 1 < flag.length) {
                            flag[i + 1] = true;
                        }
                    }
                }
            }

            return flag[nums.length - 1];

        }

        public boolean canJump(int[] nums) {
            if (nums == null || nums.length <= 0) return false;
            int end = 0;
            for (int i = 0; i < nums.length; i++) {

                if (i > end) continue; // 值是0时 前面最大都跳不过来时，

                //到这个位置后，再跳的最大范围
                if (nums[i] + i > end) {
                    end = nums[i] + i;
                }

                if (end >= nums.length - 1)
                    return true;
            }
            return false;
        }
    }
}
