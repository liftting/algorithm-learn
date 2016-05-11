package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/11.
 * 162. Find Peak Element
 * <p/>
 * A peak element is an element that is greater than its neighbors.
 * <p/>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p/>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p/>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p/>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * <p/>
 * 1,峰值元素，左右都比其小， 要求log 时间，
 * <p/>
 * 解：
 * 1，暴力 o(n)
 */
public class FindPeekElement {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.findPeakElement(new int[]{-2147483648}));
    }

    public static class Solution {
        public int findPeakElement(int[] nums) {
            long pre = -1;
            long last = -1;
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    pre = Integer.MIN_VALUE - 1l;// convert long
                } else {
                    pre = nums[i - 1];
                }

                if (i == nums.length - 1) {
                    last = Integer.MIN_VALUE - 1l;
                } else {
                    last = nums[i + 1];
                }

                int cur = nums[i];

                if (cur > pre && cur > last) {
                    return i;
                }

            }
            return -1;
        }
    }

}
