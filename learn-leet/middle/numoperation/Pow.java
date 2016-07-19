package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/13.
 * 50. Pow(x, n)
 * <p/>
 * Implement pow(x, n).
 * 解法：
 * 1，求解多个数连续相乘，可以通过 递归的方式，来处理，
 * 递归折半策略：
 * 利用上次计算的结果，
 * 每次把n缩小一半，这样n最终会缩小到0，任何数的0次方都为1，这时候我们再往回乘，如果此时n是偶数，直接把上次递归得到的值算个平方返回即可，如果是奇数，则还需要乘上个x的值
 * f(n) = f(n/2) * f(n/2) * k 奇数时会漏掉一个，
 */
public class Pow {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.myPow(10, -3));
    }

    public static class Solution {
        public double myPow(double x, int n) {

            if (n < 0) return 1 / cal(x, -n);

            return cal(x, n);

        }

        public double cal(double x, int n) {
            if (n == 0) return 1;
            double ct = cal(x, n / 2); // 拿到一般的计算结果，

            if (n % 2 == 0) return ct * ct;

            return ct * ct * x;

        }
    }


}
