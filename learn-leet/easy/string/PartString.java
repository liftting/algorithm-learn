package easy.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * 28. Implement strStr()
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * 给子字符串，求位置， KMP
 */
public class PartString {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.kmp("abcd", "e"));
        CommonUtil.show(s.strStr("abcd", "e"));
    }

    public static class Solution {
        //基本是遍历，
        public int strStr(String haystack, String needle) {
            if (needle.length() == 0)
                return 0;

            for (int i = 0; i <= haystack.length() - needle.length(); i++) {
                int n = 0;
                while (n < needle.length() && haystack.charAt(i + n) == needle.charAt(n))
                    n++;
                if (n == needle.length())
                    return i;
            }

            return -1;
        }

        // == 下面是KMP算法，

        public void calcNext(String str, int[] next) {
            int len = str.length();

            next[0] = -1;
            int k = -1;
            int j = 0;
            while (j < len - 1) {
                // k当做第一个索引位置， j 第二个索引位置
                if (k == -1 || str.charAt(j) == str.charAt(k)) {
                    //相同向前查找继续，
                    // 记录下当前的长度位置，
                    k++;
                    j++;
                    next[j] = k;
                } else {
                    //不相等时，k的位置是之前的位置，
                    k = next[k];
                }
            }

        }

        public int kmp(String text, String pattern) {

            if (pattern.length() == 0) return 0;
            if (text.length() == 0) return -1;

            int plen = pattern.length();
            int slen = text.length();

            int[] next = new int[plen];
            calcNext(pattern, next);

            int i = 0, j = 0;
            while (i < slen) {
                //递归回退索引
                while (j > 0 && text.charAt(i) != pattern.charAt(j))
                    j = next[j];

                //正常情况下，直接add
                if (text.charAt(i) == pattern.charAt(j))
                    j++;

                //已经查找结束了，
                if (j == plen) {
                    return i - j + 1;
                }

                i++;
            }

            return -1;


        }

    }

}
