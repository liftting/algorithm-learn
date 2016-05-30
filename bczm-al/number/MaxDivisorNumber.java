package number;

import java.math.BigInteger;

/**
 * Created by wm on 16/5/29.
 * 求两个数的最大公约数，
 * 1，
 * <p/>
 * <p/>
 * 解法：
 * 1，公式， f(a,b) = f(b,b % a) (b>0)  -- 一直递归划分直到有一个为0，剩下的那个数就是最大公约数，
 * <p/>
 * 2, 对于大整数来说，进行取摩运算，（其中用到了除法，） 会是很大的开销导致的，
 * 规律，：如果一个数能同时整除 x ,y 两个数，那必须能同时整除 x-y ,y ，同理反过来即可成立
 * f(x,y) = f(x-y,y)  //而如果出现 x <y 相减为负数，那么可以进行交换，直到递归为0
 */
public class MaxDivisorNumber {

    public static void main(String[] args) {


    }

    public static class Solution {
        public int calu(int x, int y) {
            return (y == 0) ? x : calu(x, y % x);
        }

        /**
         * 计算用到的是减法，可以进行处理，
         * @param x
         * @param y
         * @return
         */
        public int caluTwo(int x, int y) {
            if (x < y) {
                return caluTwo(y, x);
            }
            if (y == 0) return x;

            return caluTwo(x - y, y);
        }
    }

}
