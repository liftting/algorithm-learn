package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/3.
 * 34. Search for a Range
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p/>
 * If the target is not found in the array, return [-1, -1].
 * <p/>
 * 1,paixu，查找范围，有重复的
 * <p/>
 * 1，第一次二分查找，mid 位置，
 * 2，基于查找到的，向左，向右继续二分查找 start end
 */
public class SearchForRanage {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.searchRange(new int[]{5, 7, 7, 8, 8, 8, 8, 9, 10}, 8));
    }

    public static class Solution {
        public int[] searchRange(int[] nums, int target) {

            if (null == nums || nums.length == 0)
                return new int[]{-1, -1};

            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = (right + left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                    continue;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                    continue;
                }

                // 查找到了，继续查找 left  right
                //查左边的，left mid-1
                // 查右边的，mid +1 right;
                int leftMax = mid - 1;
                int rightMin = mid + 1;


                int resLeft = mid;
                int resRight = mid;


                while (left <= leftMax) {
                    int p = (left + leftMax) / 2;
                    if (nums[p] < target) {
                        left = p + 1;
                    } else {
                        leftMax = p - 1; // 往左扩展
                        resLeft = p;
                    }
                }

                while (rightMin <= right) {
                    int p = (rightMin + right) / 2;
                    if (nums[p] <= target) {
                        rightMin = p + 1;
                        resRight = p;
                    } else {
                        right = p - 1;
                    }
                }

                return new int[]{resLeft, resRight};

            }

            return new int[]{-1, -1};

        }
    }

}
