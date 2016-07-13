package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/13.
 * 50. Pow(x, n)
 * <p/>
 * Implement pow(x, n).
 * 解法：
 * 1，求解多个数连续相乘，可以通过 递归的方式，来处理，
 */
public class Pow {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.pow(10, -3));
    }

    public static class Solution {
        public double pow(double x, int n) {
            if (n == 0) return 1;
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }

            return n % 2 == 0 ? (pow(x * x, n / 2)) : x * pow(x * x, n / 2);

        }
    }


}
