package easy.dongtaiguihua;

/**
 * Created by wm on 16/3/21.
 * <p/>
 * 198. House Robber
 * <p/>
 * 给出一个数组，找出组合数值，使得和最大，但是每个数是不相邻的，
 */
public class HouseRobber {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.rob(new int[]{1}));
    }

    public static class Solution {
        /**
         * 处理和 爬楼梯一致的
         * 下面就是规划方程
         * f(i) = max(f(i-1), f(i-2)+nums[i])
         * <p/>
         * 优化：避免重复递归计算，要进行缓存
         *
         * @param nums
         * @return
         */
        public int rob(int[] nums) {

            if (nums == null || nums.length == 0) return 0;
            Integer[] cache = new Integer[nums.length];

            return calute(nums, nums.length - 1, cache);

        }

        public int calute(int nums[], int i, Integer[] cache) {
            if (i == 0) return nums[0];
            if (i == 1) return Math.max(nums[0], nums[1]);


            if (cache[i] != null) return cache[i];

            cache[i] = Math.max(calute(nums, i - 1, cache), calute(nums, i - 2, cache) + nums[i]);

            return cache[i];

        }


    }

}
