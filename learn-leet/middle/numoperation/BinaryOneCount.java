package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/24.
 * <p/>
 * 1, 求解一个二进制数中，1 的个数
 * <p/>
 * 10100101  -- 4 个
 * <p/>
 * 解：
 * 1， %2  /2
 * 2, 右边移位，  每次与运算即可，
 */
public class BinaryOneCount {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.caluteThree(3));
        CommonUtil.show(s.caluteThree(2));
    }

    public static class Solution {
        public int calute(int num) {

            int ct = 0;
            while (num != 0) {
                if (num % 2 == 1) {
                    ct++;
                }
                num = num / 2;
            }

            return ct;

        }

        /**
         * <<      :     左移运算符，num << 1,相当于num乘以2
         * <p/>
         * >>      :     右移运算符，num >> 1,相当于num除以2
         * <p/>
         * >>>    :     无符号右移，忽略符号位，空位都以0补齐
         *
         * @param num
         * @return
         */
        public int caluteTwo(int num) {

            int ct = 0;
            int cpVal = 0x01;

            while (num != 0) {
                ct += (cpVal & num);// 00 与都为0，最后一位判断

                num = num >> 1;

            }
            return ct;

        }

        /**
         * 2中所述方法的循环次数始终为8，有一种方法可以减少这个循环次数。就是采用减1再进行与的运算，这样每进行一次，就会少一个1.
         * <p/>
         * 比如： 0010 0110 减1得 0010 0101 &0010 0110等于0010 0100.原因在于比如r1r2...rn，如果最后面位1的一位为rk，则该数减1之后二进制的表示形式中rk肯定为0，但是r(k+1)...rn则全部为1，与原来的数进行与操作不会印象到rk前面的1的个数，因此每进行一次，则可以消去一个二进制1。
         *
         * @param num
         * @return
         */
        public int caluteThree(int num) {
            int ct = 0;
            while (num != 0) {
                num &= num - 1; // 直接过滤1， 跳过0的比较
                ct++;
            }
            return ct;
        }

    }

}
