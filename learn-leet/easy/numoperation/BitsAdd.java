package easy.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * <p/>
 * 67. Add Binary
 * <p/>
 * a = "11"
 * b = "1"
 * Return "100".
 * <p/>
 * 二进制数的相加
 */
public class BitsAdd {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.addBinary("11", "001"));
    }

    public static class Solution {
        public String addBinary(String a, String b) {
            StringBuilder sb = new StringBuilder();
            int alen = a.length();
            int blen = b.length();
            int cc = 0;
            for (int i = alen - 1, j = blen - 1; i >= 0 || j >= 0; i--, j--) {
                int ca = 0;
                int cb = 0;
                if (i >= 0) {
                    ca = a.charAt(i) - '0';
                }
                if (j >= 0) {
                    cb = b.charAt(j) - '0';
                }
                sb.insert(0, cc ^ ca ^ cb);

                cc = (cc + ca + cb) / 2;

            }

            if (cc > 0) sb.insert(0, cc);

            return sb.toString();


        }
    }

}
