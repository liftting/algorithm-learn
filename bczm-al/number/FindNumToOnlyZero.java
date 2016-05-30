package number;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/29.
 * 给一个正整数N， 求一个最小的M，（M>1)，使得N * M 的十进制表中只包含1和0
 * <p/>
 * 解法：
 * 1，暴力解决，
 * 遍历，如果相乘的结果， （进行不断的取余操作），如果符合条件，满足
 */
public class FindNumToOnlyZero {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.calu(99));
    }

    public static class Solution {

        public int calu(int n) {
            for (int i = 2; i < Integer.MAX_VALUE; i++) {
                double d = i * n;
                if (isOk(d)) {
                    CommonUtil.show(String.valueOf(d));
                    return i;
                }
            }

            return -1;
        }

        private boolean isOk(double m) {
            while (m != 0) {
                if (m % 10 > 1) return false;

                m /= 10;
            }

            return true;

        }

    }


}
