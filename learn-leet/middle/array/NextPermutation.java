package middle.array;

import java.util.Arrays;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/25.
 * 31. Next Permutation
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3 -- 已经最大了，得下一个
 * 1,1,5 → 1,5,1
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * 得到下一个排列，
 * <p/>
 * 如果当前已经是最大了，那么得到最小的那个排列，
 * <p/>
 * 求一个排列的下一个排列算法， 网上的
 */
public class NextPermutation {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.nextPermutation(new int[]{16, 27, 25, 23, 25, 16, 12, 9, 1, 2, 7, 20, 19, 23, 16, 0, 6, 22,
                16, 11, 8, 27, 9, 2, 20, 2, 13, 7, 25, 29, 12, 12, 18, 29, 27, 13, 16, 1, 22, 9, 3, 21, 29, 14, 7, 8, 14, 5, 0, 23, 16, 1, 20});
    }

    public static class Solution {
        public void nextPermutation(int[] nums) {
            //递归求出所有排列，下一个就是

            if (nums == null || nums.length <= 1) return;

            calute(nums);
        }

        public void calute(int[] str) {

            int t = findReplacePoint(str);

            if (t < 0) { //排到最后了，
                Arrays.sort(str);
//                CommonUtil.show(str);
            } else {
                int ct = findMaxValue(str, str[t], t + 1);
                swap(str, t, ct);
                reverse(str, t + 1);
//                CommonUtil.show(str);
            }
        }

        public int findReplacePoint(int[] str) {
            int last = str[str.length - 1];
            for (int i = str.length - 2; i >= 0; i--) {
                if (last > str[i]) {
                    return i;
                } else {
                    last = str[i];
                }
            }
            return -1;
        }

        private void reverse(int[] str, int index) {
            int i = index;
            int j = str.length - 1;
            int mid = (i + j) / 2;
            while (i <= mid) {
                swap(str, i, j);
                i++;
                j--;
            }
        }

        /**
         * 比替换数大的最小数
         *
         * @return
         */
        public int findMaxValue(int[] str, int te, int index) {
            int max = 10;
            int d = -1;
            for (int i = index; i < str.length; i++) {
                if (str[i] > te && str[i] < max) {
                    max = str[i];
                    d = i;
                }
            }

            return d;
        }

        public void swap(int[] str, int x, int y) {
            int temp = str[x];
            str[x] = str[y];
            str[y] = temp;
        }

    }

}
