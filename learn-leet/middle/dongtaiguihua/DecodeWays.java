package middle.dongtaiguihua;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/11.
 * 91. Decode Ways
 * <p/>
 * 两个字母表示一个数字，或一个字母表示一个数字
 * f(n) = {f(n-1) + f(n-2)}
 * <p/>
 * "12"
 * <p/>
 * case :
 * 00 -- 0
 * 01 -- 0
 */
public class DecodeWays {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.numDecodings("01"));
    }

    public static class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() <= 0) return 0;
            int[] f = new int[s.length()];

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (i >= 1) {

                    //两个 满足两个数，那么必须>=10
                    char pre = s.charAt(i - 1);
                    int num = 10 * (pre - '0') + (ch - '0');

                    //f(n-2)
                    if (num >= 10 && num <= 26) {
                        //符合两个
                        if (i > 1) {
                            f[i] += f[i - 2];
                        } else {
                            f[i] += 1;
                        }
                    }

                    //f(n-1)
                    if ('1' <= ch && ch <= '9') {
                        f[i] += f[i - 1];
                    }

                } else {
                    if ('1' <= ch && ch <= '9') {
                        f[i] = 1;
                    }
                }
            }

            return f[s.length() - 1];

        }
    }

}
