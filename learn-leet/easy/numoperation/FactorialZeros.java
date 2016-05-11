package easy.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/11.
 * 172. Factorial Trailing Zeroes
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p/>
 * Note: Your solution should be in logarithmic time complexity.
 * <p/>
 * 1,阶乘中 0 的个数
 * 2， 0是 *10 (偶数*5得到带*10)出来的，
 * 3, 1 * 2 *3 *4 *5;
 */
public class FactorialZeros {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.trailingZeroes(1808548329));
    }

    public static class Solution {
        // 这种只能计算出最后面是0的数字， 超时，对于大数，
        public int trailingZeroesOne(int n) {
            long sum = 1;
            int count = 0;
            for (int i = 1; i <= n; i++) {
                sum *= i;
                if (sum % 10 == 0) {
                    count++;
                    sum = sum / 10;
                }
            }

            return count;

        }

        //就是5的倍数，  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        public int trailingZeroes(int n) {
            int count = 0;
            while (n > 0) {
                n /= 5;
                count += n;
            }

            return count;

        }
    }

}
