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
 * <p/>
 * //每个合数都可以写成几个质数相乘的形式。其中每个质数都是这个合数的因数，叫做这个合数的分解质因数。 分解质因数只针对合数。
 * 合数：合数，数学用语，英文名为Composite number，指自然数中除了能被1和本身整除外，还能被其他的数整除（不包括0)的数。
 * <p/>
 * 质数（素数）：与之相对的是质数（因数只有1和它本身，如2,3,5,7,11,13等等，也称素数）
 * <p/>
 * <p/>
 * // 质因数的分解： N! =
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

        //公式：N! = 2x * 3y * 5z  每次都计算因式分解的5的指数
        public int solutinOne(int n) {
            int ct = 0;
            for (int i = 1; i <= n; i++) {
                int j = i;
                while (j % 5 == 0) {
                    ct++;
                    j /= 5;
                }
            }
            return ct;
        }
    }

}
