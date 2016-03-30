package easy.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/29.
 * 8. String to Integer (atoi)
 * Implement atoi to convert a string to an integer.
 * <p/>
 * "  -0012a42"   -12
 */
public class StringToInteger {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.myAtoi("9223372036854775809"));
    }

    public static class Solution {
        /**
         * 特殊情况下，long已经越界，
         * double 占用8个字节，但是取值范围要大，浮点表示，采用的存储方式不同
         *
         * @param str
         * @return
         */
        public int myAtoi(String str) {
            //字符遍历获取，
            if (str == null || str.isEmpty()) return 0;
            str = str.trim();
            boolean flag = false;
            boolean isLow = false;

            double sum = 0; //
            double deg = 1;
            for (int i = str.length() - 1; i >= 0; i--) {
                char ch = str.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    sum += (int) (ch - '0') * deg;
                    deg = deg * 10;
                } else if (ch == '-' || ch == '+') {
                    if (flag) return 0;
                    flag = true;

                    if (ch == '-')
                        isLow = true;

                } else {
                    sum = 0;
                    deg = 1;
                    isLow = false;
                    flag = false;
                }
            }
            if (!isLow && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;

            if (isLow && -sum < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            return (int) (isLow ? -sum : sum);
        }
    }

}
