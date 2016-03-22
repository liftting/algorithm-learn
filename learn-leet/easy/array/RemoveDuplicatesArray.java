package easy.array;

import easy.list.ListNode;

/**
 * Created by wm on 16/3/22.
 * <p/>
 * 26. Remove Duplicates from Sorted Array
 * <p/>
 * For example,
 * Given input array nums = [1,1,2],
 * <p/>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesArray {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1, 1, 2, 2, 3, 4, 5, 6};
        int len = s.removeDuplicates(nums);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static class Solution {
        /**
         * 去掉数组重复元素，并向前移动，这里就是两个游标指向，然后进行替换操作
         *
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {

            if (nums == null || nums.length <= 0) return 0;

            int i = 0;
            int j = 1;

            int count = nums.length;

            while (j < nums.length) {

                if (nums[i] != nums[j]) {
                    nums[i + 1] = nums[j];
                    i++;
                    j++;
                } else {
                    count--;
                    j++;
                }

            }

            return count;
        }
    }

}
