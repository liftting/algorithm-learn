package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/19.
 * 357. Count Numbers with Unique Digits
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * <p/>
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 * <p/>
 * 统计数据在0 - 10(n) 中，没有叠词数据的个数，（22，222，2222,221)
 * hit:
 * 1,A direct way is to use the backtracking approach
 * <p/>
 * Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
 * This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
 * <p/>
 * 4, Let f(k) = count of numbers with unique digits with length equals k.
 * <p/>
 * 5, f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * 解法：
 * 1，暴力遍历，每一个数的位数都相同进行遍历，
 * 2，回溯，1-9 进行试探性的往前走，
 * <p/>
 * 1 0
 * 1 2
 * 思路是，从第一位开始，标记是否用过这个数字，用过就不能用了，当选择位数时，把其他的数字遍历进去，然后进行递归回溯，深搜
 */
public class CountNumWithUnqiueDigits {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.countNumbersWithUniqueDigits(0));
    }

    public static class Solution {

        private int ct; //没有重复的数字

        public int countNumbersWithUniqueDigits(int n) {
            int[] tag = new int[10];
            int max = (int) Math.pow(10, n);
            for (int i = 1; i < 10; i++) {
                if (i >= max) continue;
                tag[i] = 1;
                ct++;
                search(i, tag, max);
                tag[i] = 0;
            }

            return ct + 1;
        }

        private void search(int m, int[] tag, int max) {

            for (int i = 0; i < 10; i++) {
                int sum = m * 10 + i;
                if (sum < max && tag[i] == 0) {
                    //这个位置没用过
                    tag[i] = 1;
                    ct++;
                    search(sum, tag, max);
                    tag[i] = 0;
                }
            }
        }
    }

    public static class SimpleSolution {
        //有一个公式 f(k) = 9 * 9 * 8 .. (9-k+2)
//        public int countNumbersWithUniqueDigits(int n) {
//
//        }

    }

    public static class OtherSolution {
        //这个题意理解有问题，error
        public int countNumbersWithUniqueDigits(int n) {
            int max = (int) Math.pow(10, n);
            int ct = 0;
            for (int i = 1; i <= 9; i++) {
                ct += search(i, max);
            }

            return max - ct;
        }

        private int search(int m, int max) {
            int ct = 0;
            // 22 ,221,220,223,

            int sum = m;
            do {
                sum = sum * 10 + m;
                if (sum < max) {
                    System.out.println(sum);
                    ct++;
                }
                //当数据大于百时，需要再去重， 220 221  2201 2202  2221 2220
            } while (sum < max);

            return ct;

        }
    }


}
