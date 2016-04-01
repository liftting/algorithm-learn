package middle.numoperation;

import java.math.BigDecimal;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/31.
 * 29. Divide Two Integers
 * 两个数相除，
 * Divide two integers without using multiplication, division and mod operator.
 * <p/>
 * If it is overflow, return MAX_INT.
 * <p/>
 * 6 /3 =
 * <p/>
 * 二进制除法，注意溢出
 * <p/>
 * TODO 没理解，后续了解
 */
public class DivideNumber {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.BinaryDivide(100, 50));
    }

    public static class Solution {
        //加法
        int BinaryAdd(int a, int b) {
            int carry, add;
            do {
                add = a ^ b; //该操作得到本位的加法结果
                carry = (a & b) << 1; //该操作得到该位对高位的进位值
                a = add;
                b = carry;
            } while (carry != 0); //循环直到某次运算没有进位，运算结束
            return add;
        }

        //减法
        int BinarySub(int a, int b) {
            return BinaryAdd(a, BinaryAdd(~b, 1));
        }

        int BinaryMultiply(int a, int b) {
            return 0;
        }

        //除法
        int BinaryDivide(int a, int b) {
            boolean neg = (a > 0) ^ (b > 0);
            if (a < 0)
                a = -a;
            if (b < 0)
                b = -b;
            if (a < b)
                return 0;
            int msb = 0;
            //msd记录除数需要左移的位数
            for (msb = 0; msb < 32; msb++) {
                if ((b << msb) >= a)
                    break;
            }
            int q = 0; //记录每次除法的商
            for (int i = msb; i >= 0; i--) {
                if ((b << i) > a)
                    continue;
                q |= (1 << i);
                a -= (b << i);
            }
            if (neg)
                return -q;
            return q;
        }
    }

}
