package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/13.
 * 367. Valid Perfect Square
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * <p/>
 * Note: Do not use any built-in library function such as sqrt.
 * <p/>
 * Example 1:
 * <p/>
 * Input: 16
 * Returns: True
 * Example 2:
 * <p/>
 * Input: 14
 * Returns: False
 * <p/>
 * 求解开平方
 * 解法：
 * 1，二分查找，相当于在当前数据中不断的二分，进行平方操作，
 * 数据溢出，
 * 因为给的是一个正数，那么就是2（32）次方的数字，比Integer.MAX_VALUE要大一倍，
 * 处理时long来处理
 * <p/>
 * 2， 神奇的处理方法：
 * 1 = 1
 * 4 = 1 + 3
 * 9 = 1 + 3 + 5
 * 16 = 1 + 3 + 5 + 7
 * 25 = 1 + 3 + 5 + 7 + 9
 * 36 = 1 + 3 + 5 + 7 + 9 + 11
 * ....
 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
 *
 * n * n = ((1 + 2n -1) * n) / 2
 *
 */
public class SquareMath {


    public static void main(String[] args) {
        OtherSolution s = new OtherSolution();
        System.out.println(Integer.MAX_VALUE);
        CommonUtil.show(s.isPerfectSquare(2147483647));
        CommonUtil.show(s.isPerfectSquare(100));
    }

    public static class Solution {
        public boolean isPerfectSquare(int num) {

            long lnum = num;

            long begin = 1;
            long end = lnum;
            long cal;
            long mid;

            while (begin <= end) {
                mid = begin + ((end - begin) >> 1); // 数大时溢出
                cal = mid * mid;
                System.out.println("begin mid:" + mid);

                if (cal < lnum) {
                    begin = mid + 1;
                } else if (cal > lnum) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }

            return false;

        }


    }

    public static class OtherSolution {
        public boolean isPerfectSquare(int num) {
            int i = 1;
            while (num > 0) {
                num -= i;
                i += 2;
            }
            return num == 0;
        }
    }
}
