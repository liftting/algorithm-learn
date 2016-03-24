package easy.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/24.
 * <p/>
 * 88. Merge Sorted Array
 * 合并数组，已经排序的
 * <p/>
 * 这个就是归并排序
 */
public class MergeSortArray {

    public static void main(String[] args) {
        int[] two = new int[]{2, 4, 6, 8, 10, 19};
        int[] one = new int[]{
                1, 3, 4, 11, 17, 18, 20, 0, 0, 0, 0, 0, 0, 0, 0
        };

        Solution s = new Solution();
        s.merge(one, 7, two, 6);
        CommonUtil.show(one);

    }

    public static class Solution {
        /**
         * 1 2 3 7 11 17 18           18 19
         * 2 4 6 8 10  19
         * 比较时，如果num2 大于 num1，交换，
         * <p/>
         * 数组合并，使用两个游标，因为涉及到 存储，不能完成，
         * 因为是已经排序的，那么，以第一个数组为基础，在2中查找到在这个元素之前的
         * <p/>
         * 最基础，得到元素，然后插入，
         * 插入排序，
         * <p/>
         * 逻辑复杂
         * 还有一种方式，元素后放，
         * <p/>
         * <p/>
         * ==   网上更好的方式
         * <p/>
         * 从后往前进行归并 ，避免后移问题，
         *
         * @param nums1
         * @param m
         * @param nums2
         * @param n
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {

            if (nums1 == null || nums2 == null) return;

            int last = m + n - 1;
            int p = m - 1;
            int q = n - 1;

            while (p >= 0 && q >= 0) {

                if (nums2[q] >= nums1[p]) {
                    nums1[last] = nums2[q];
                    q--;
                } else {
                    nums1[last] = nums1[p];
                    p--;
                }
                last--;
            }

            while (q >= 0) {
                nums1[last] = nums2[q];
                q--;
                last--;
            }

        }
    }

}
