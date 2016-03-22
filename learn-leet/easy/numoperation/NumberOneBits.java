package easy.numoperation;

/**
 * Created by wm on 16/3/16.
 * 191. Number of 1 Bits
 */
public class NumberOneBits {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.hammingWeight(Integer.MAX_VALUE);
    }

    public static class Solution {
        // you need to treat n as an unsigned value

        /**
         * 转换过程计算
         *
         * @param n
         * @return
         */
        public int hammingWeight(int n) {

            if (n > Integer.MAX_VALUE) return 32;

            long ln = n;

            int cnt = 0;
//            String s = "";
            while (ln != 0) {
                long dig = ln % 2;
                if (dig != 0) {
                    cnt++;
                }
//                s = dig + s;
                ln /= 2;
            }
//            System.out.println(s);
            return cnt;
        }


        /**
         * java  &与  ~非  |或  ^异或，
         *
         * @param n
         * @return
         */
        public int goodCalculate(int n) {
            int count = 0;
            int y;
            while (n != 0) {
                count++;
                //去1
                /***
                 * 异或运算符是用符号“^”表示的，其运算规律是：两个操作数的位中，相同则结果为0，不同则结果为1。
                 * 11
                 *  n : 0001101
                 *  n-1 : 0001100
                 *  ~(n-1):1110011
                 * n& ~(n-1) :0000001
                 * n^(n& ~(n-1)) : 0001100
                 */
                n ^= n & ~(n - 1);
            }
            return count;
        }
    }

}
