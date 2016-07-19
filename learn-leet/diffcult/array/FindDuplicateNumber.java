package diffcult.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/18.
 * 287. Find the Duplicate Number
 * <p/>
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * <p/>
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p/>
 * 分析：
 * 重复数字， 有一个数字是重复的 在 1-n+1 中，
 * 1，不能修改数组，只读
 * 2，必须o(1)的空间复杂度
 * 3,时间复杂度 要小于 o(n2)
 * 4，只有一个重复的数字，但是可能重复多次，不仅仅是一次，
 * <p/>
 * <p/>
 * 7 8 9 1 2 1 3 4 5 6
 * 解法：
 * 1，排序，然后查找， 会导致数组修改 o(nlogn)
 * 2，哈希表法， 遍历数据，存入hash表，如果存在则是重复，
 * 3，暴力法，时间o(n2) 空间 o(1)
 * 4，二分法，o(nlogn) o(1)空间复杂度
 * 抽屉原理:
 * 如果每个抽屉代表一个集合，每一个苹果就可以代表一个元素，假如有n+1个元素放到n个集合中去，其中必定有一个集合里至少有两个元素
 * <p/>
 * 这题， 1-n 各咱一个位置，只有一个重复的数字，
 * <p/>
 * 1 1 2 3 4 5 6  mid =
 * 1 2 3 4 5   mid 3
 * 参考：https://segmentfault.com/a/1190000003817671
 *
 * TODO: 暂时不理解，原理，
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.findDuplicate(new int[]{2, 3, 4, 5, 6, 1, 1}));
    }

    public static class Solution {
        public int findDuplicate(int[] nums) {

            int begin = 1;
            int end = nums.length - 1;


            while (begin < end) {
                int mid = begin + (end - begin) / 2;

                int ct = 0;
                for (int num : nums) {
                    if (num <= mid) {
                        ct++;
                    }
                }

                if (ct <= mid) { // dup is on the second half
                    begin = mid + 1;
                } else {
                    end = mid;
                }
            }
            return begin;
        }
    }

}
