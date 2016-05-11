package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/11.
 * 81. Search in Rotated Sorted Array II
 *
 * 1，因为有重复元素，而判断的基准是和最右边比较的，所以
 * add 两者相等的条件， 直接在左右来回寻找即可，
 *
 */
public class SearchRotatedSoryArrayTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.search(new int[]{4, 5, 6, 7, 0, 1, 2, 2}, 2));
    }

    public static class Solution {
        public boolean search(int[] nums, int target) {

            return search(nums, 0, nums.length - 1, target);

        }

        public boolean search(int[] nums, int left, int right, int target) {
            if (left > right) return false;

            int mid = (left + right) / 2;

            if (nums[mid] == target) return true;

            //当有重复元素时，不知道左右，就双向再次查找下位置，因为不要求返回位置，只需boolean即可，
            if (nums[mid] == nums[right]) {
                return search(nums, left, mid - 1, target) || search(nums, mid + 1, right - 1, target);
            }

            if (nums[mid] > nums[right]) {
                //中间比右边大，那么左边就是有序的，右边中间的特殊点

                if (target < nums[left] || target > nums[mid]) {
                    // find right
                    return search(nums, mid + 1, right, target);

                } else {
                    //find left
                    return search(nums, left, mid - 1, target);
                }


            } else {
                //中间的比右边的小，右边是有序的，左边有中间点
                if (target < nums[mid] || target > nums[right]) {
                    //find left
                    return search(nums, left, mid - 1, target);
                } else {
                    //find right
                    return search(nums, mid + 1, right, target);
                }
            }

        }
    }

}
