package middle.numoperation;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/6/6.
 * 343. Integer Break
 * <p/>
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 * <p/>
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4). --
 * <p/>
 * Note: you may assume that n is not less than 2.
 * <p/>
 * 意思：
 * There is a simple O(n) solution to this problem.
 * You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 * <p/>
 * <p/>
 * 整数拆分，求乘积最大，
 * <p/>
 * so:
 * 1, >=3 最小试探，因为小数越多，相乘越大，
 * <p/>
 * 应该以3 来试探 ，剩下的以2 来试探，
 */
public class IntegerBreak {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.integerBreak(7));
    }

    public static class Solution {
        public int integerBreak(int n) {

            if (n < 4) return n - 1;

            int sum = 1;

            while (n > 2) {
                sum *= 3;
                n -= 3;
            }

            if (n == 1) {
                // 3 3 3 1
                return (sum / 3) * 4;
            }
            if (n == 2) return sum * 2;

            if (n == 0) return sum;

            return -1;

        }
    }

}
