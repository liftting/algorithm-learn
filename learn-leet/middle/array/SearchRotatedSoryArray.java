package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/3.
 * <p/>
 * 33. Search in Rotated Sorted Array
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p/>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p/>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p/>
 * You may assume no duplicate exists in the array.
 * 1,没有重复元素
 * 2，暴力时间O(n) -- O(logN)
 * 3，paixu，+ 二分查找 o(logN)
 * <p/>
 * 4，规律：
 * 借由二分法，只不过在找到中间的数了之后，需要比较中间数和最右数（或者最左数），有这样的结论：
 * <p/>
 * 1. 如果中间数大于最右数，那么最大最小数的分界点在右半支，而左半支严格单调递增，在这种情况下通过比较左半支的首尾两个数，判断目标数在不在左半支内，如果不在，就一定在右半支内。
 * 2. 如果中间数小于最右数，那么最大最小数的分界点在左半支，而右半支严格单调递增，在这种情况下通过比较右半支的首尾两个数，判断目标数在不在右半支内，如果不在，就一定在左半支内。
 */
public class SearchRotatedSoryArray {

    public static void main(String[] args) {
        OtherSolution s = new OtherSolution();
        CommonUtil.show(s.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
    }

    public static class Solution {
        public int search(int[] nums, int target) {

            return search(nums, 0, nums.length - 1, target);

        }

        public int search(int[] nums, int left, int right, int target) {
            if (left > right) return -1;

            int mid = (left + right) / 2;

            if (nums[mid] == target) return mid;

            //当有重复元素时，
//            if (nums[mid] == nums[right]) {
//                return search(nums, left, mid - 1, target) || search(nums, mid + 1, right - 1, target);
//            }

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


    public static class OtherSolution {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[mid] > nums[right]) {
                    if (target < nums[left] || target > nums[mid]) {
                        // find right
                        left = mid + 1;

                    } else {
                        //find left
                        right = mid - 1;
                    }
                } else {
                    if (target < nums[mid] || target > nums[right]) {
                        //find left
                        right = mid - 1;
                    } else {
                        //find right
                        left = mid + 1;
                    }
                }
            }

            return -1;

        }

    }

}
