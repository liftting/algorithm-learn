package middle.array;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/10.
 * <p/>
 * 80. Remove Duplicates from Sorted Array II
 * <p/>
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p/>
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p/>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 * <p/>
 * 重复元素最多两次，又是排过的有序
 * 1,
 */
public class RemoveDuplicateSortArrayTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.removeDuplicates(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4}));
        CommonUtil.show(s.removeDuplicates(new int[]{1, 2, 2, 2, 3, 3, 4, 4}));
        CommonUtil.show(s.removeDuplicates(new int[]{1, 2, 3, 4, 5}));

    }

    public static class Solution {
        public int removeDuplicates(int[] nums) {

            if (nums == null || nums.length <= 0) return 0;

            List<Integer> dataList = new ArrayList<Integer>();

            int len = nums.length;
            int compare = nums[0];
            // first
            dataList.add(compare);

            int i = 1;
            int count = nums.length;
            boolean isMoreTwo = false;
            boolean isAddData = true;

            while (i < len) {
                isAddData = true;
                if (nums[i] == compare) {
                    if (!isMoreTwo) {
                        isMoreTwo = true;
                    } else {
                        count--;
                        isAddData = false;
                        //要进行数据删除操作
                    }
                } else {
                    compare = nums[i];
                    isMoreTwo = false;
                }

                if (isAddData) {
                    dataList.add(nums[i]);
                }
                i++;
            }

            for (int k = 0; k < dataList.size(); k++) {
                nums[k] = dataList.get(k);
            }

            return count;

        }

        public int removeDuplicates2(int[] nums) {
            int i = 0;
            for (int n : nums)
                if (i < 2 || n > nums[i - 2])
                    nums[i++] = n;
            return i;
        }
    }

}
