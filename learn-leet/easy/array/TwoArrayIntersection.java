package easy.array;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/30.
 * 349. Intersection of Two Arrays
 * <p/>
 * Given two arrays, write a function to compute their intersection.
 * <p/>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * <p/>
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 * <p/>
 * 就是公共字串，而且需要全匹配的模式, （重复元素，去重
 * [1,2]
 * [1,1]
 * <p/>
 * expect : [1]
 * <p/>
 * 解法
 * 1,遍历，暴力， o(m * n)
 */
public class TwoArrayIntersection {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.intersection(new int[]{1, 2}, new int[]{1, 1}));
    }

    public static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            int[] array = new int[0];

            if (nums1 == null || nums2 == null) return array;

            int len1 = nums1.length;
            int len2 = nums2.length;

            int[] maxArray, minArray;

            if (len1 > len2) {
                maxArray = nums1;
                minArray = nums2;
            } else {
                maxArray = nums2;
                minArray = nums1;
            }

            int k = 0;

            for (int i = 0; i < maxArray.length; i++) {
                k = i;
                for (int j = 0; j < minArray.length && k < maxArray.length; j++) {
                    if (j == minArray.length - 1 && maxArray[k] == minArray[j]) {
                        return convertData(minArray);
                    }

                    if (maxArray[k] == minArray[j]) {
                        k++;
                    } else {
                        break;
                    }
                }
            }

            return array;


        }

        private int[] convertData(int[] data) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < data.length; i++) {
                set.add(data[i]);
            }

            int[] result = new int[set.size()];
            int k = 0;

            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                result[k++] = iterator.next();
            }

            return result;
        }

    }

    public class OtherSolution {
        /**
         * 只要包含一个元素，也就算有一个交汇点，
         *
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<Integer>();
            Set<Integer> intersect = new HashSet<Integer>();
            for (int i = 0; i < nums1.length; i++) {
                set.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (set.contains(nums2[i])) {
                    intersect.add(nums2[i]);
                }
            }
            int[] result = new int[intersect.size()];
            int i = 0;
            for (Integer num : intersect) {
                result[i++] = num;
            }
            return result;
        }
    }

}
