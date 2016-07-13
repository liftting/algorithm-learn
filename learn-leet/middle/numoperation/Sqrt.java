package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/13.
 * 69. Sqrt(x)
 * Implement int sqrt(int x).
 * <p/>
 * Compute and return the square root of x.
 * 开平方根，直接返回整数，
 * 1，浮点数，带精度的计算方式  , 还是利用的是 二分查找的方式，不过每次都要带上精度来计算，
 *
 * 2,因为精度的计算不准确，可以采用往前试探的方式判断下，
 */
public class Sqrt {

    public static void main(String[] args) {

        Solution s = new Solution();
        CommonUtil.show(s.mySqrt(99));
        CommonUtil.show(s.mySqrt(2147483647));
        CommonUtil.show(s.mySqrt(9));

    }

    public static class Solution {
        public int mySqrt(int x) {
            long result = sqrt(x);
            return (int) result;
        }

        /**
         * 99
         * 81 100
         *
         * @param num
         * @return
         */
        private long sqrt(int num) {

            long lnum = num;

            long begin = 1;
            long end = lnum;
            long cal;
            long mid;
            long next;

            while (begin <= end) {
                mid = begin + ((end - begin) >> 1); // 数大时溢出

                cal = mid * mid;
                next = (mid + 1) * (mid + 1);

                if (lnum < cal) {
                    end = mid - 1;
                } else if (lnum > next) {
                    begin = mid + 1;
                } else if (lnum < next && lnum >= cal) {
                    //前一个大，后一个小
                    return mid;
                } else if (lnum == next) {
                    return mid + 1;
                }

            }
            return 0;
        }
    }
}
