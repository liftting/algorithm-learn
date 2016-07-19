package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/19.
 * 372. Super Pow
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 * <p/>
 * Example1:
 * <p/>
 * a = 2
 * b = [3]
 * <p/>
 * Result: 8
 * Example2:
 * <p/>
 * a = 2
 * b = [1,0]
 * <p/>
 * Result: 1024
 * <p/>
 * b因为是个数组，里面存储的如果计算会越界，
 * 分步计算，
 * <p/>
 * 解法：
 * 1，暴力，
 * 如果分数组的位置进行 计算，然后在将这些数字再合并起来，
 * 可以参考，之前的Pow()进行二分拆解计算的方式，
 * 对每个数据进行计算
 * 然后每个计算时进行折半
 * <p/>
 * [1,1,0]
 * 2(123)  = 2(1 * 100) * 2(2*10) * 2(3)  =  2(1) * 2(100) * 2(2) * 2(10) * 2(3)
 * <p/>
 * ==
 * 1(10) * 2(1)
 * (1(10) * 2(1))(10) * 2(2)
 *
 *
 * 1467
 * （3*5*5）%2 = 0 75 % 2 = 1
 * 3%2 * 5%2 * 5%2 = 1
 *
 *
 */
public class SuperPow {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.superPow(2, new int[]{1, 2, 3}));

        OtherSolution os = new OtherSolution();
        CommonUtil.show(os.superPow(2, new int[]{2,0}));
    }

    public static class Solution {
        public long superPow(int a, int[] b) {
            long sum = 1;
            for (int i = 0; i < b.length; i++) {
                sum = cal(sum, 10) * cal(a, b[i]);
            }
            CommonUtil.show(sum); // 这里很容易越界， 根据乘法的计算规则，带上%后计算是可以的，
            return sum % 1337;
        }

        public long cal(long a, int n) {
            if (n == 0) return 1;
            if (n == 1) return a;

            return cal(a, n / 2) * cal(a, n - n / 2);

        }
    }

    /**
     * 下面的计算是正确的，
     *
     */
    public static class OtherSolution {
        public int superPow(int a, int[] b) {
            int sum = 1;
            for (int i = 0; i < b.length; i++) {
                sum = cal(sum, 10) * cal(a, b[i]) % 1337;
            }
            return sum;
        }

        public int cal(int a, int n) {
            if (n == 0) return 1;
            if (n == 1) return a % 1337; //这里也要进行取余，因为这个是计算的结果，

            return cal(a % 1337, n / 2) * cal(a % 1337, n - n / 2) % 1337;

        }
    }

}
