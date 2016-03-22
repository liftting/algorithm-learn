package easy.array;

/**
 * Created by wm on 16/3/22.
 * 27. Remove Element
 * <p/>
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2
 * 将val在数组中移除掉，返回新的数组长度
 */
public class RemoveElement {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3};
        int len = s.removeElement(nums, 3);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static class Solution {
        public int removeElement(int[] nums, int val) {
            if (nums == null || nums.length <= 0) return 0;

            int i = 0;
            int j = 1;

            int ct = 0;
            if (nums[i] == val) ct++;

            while (j < nums.length) {

                if (nums[j] == val) ct++;

                if (nums[i] != val) i++;

                if (nums[i] == val && nums[j] != val) {
                    nums[i] = nums[j];
                    nums[j] = val;
                    i++;
                }

                j++;

                // 2 3   --  2 2  -- 3 3

            }

            return nums.length - ct;
        }
    }

}
