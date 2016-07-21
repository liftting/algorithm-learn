package diffcult.array;

/**
 * Created by wm on 16/7/20.
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p/>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p/>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p/>
 * You may assume no duplicate exists in the array.
 * <p/>
 * 有序的数组，在某一个位置被旋转了，
 * 查找一个数字，返回位置，没有返回 -1
 * 这里没有重复的数字
 * <p/>
 * 解法
 * 1，暴力查找， o(n)
 * 2,  这个可以使用二分的查找方式， 参考另外一个SearchRotatedSoryArray.java
 */
public class SearchRotatedArray {

    public static void main(String[] args) {

    }

    public static class Solution {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length <= 0) return -1;
            for (int i = 0; i < nums.length; i++) {

                if (nums[i] == target) return i;

            }

            return -1;
        }
    }

}
