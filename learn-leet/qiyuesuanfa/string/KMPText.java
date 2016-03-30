package qiyuesuanfa.string;

/**
 * Created by wm on 16/3/9.
 */
public class KMPText {

    public static void main(String[] args) {
        String text = "abcdabddcdabnc";
        String pattern = "abeab";
        kmp(text, pattern);

    }

    /**
     * 这里统计的是位置，前面的 字符串中，最大的符合串，符合规则是，前缀串  和  后缀串相同的，
     * abeabc
     * 0 -1
     * 1 a pre - “”  - 0
     * 2 b pre - a  - 0
     * 3 e pre -ab  - 0
     * 4 a pre -abe - 0
     * 5 b pre -abea - 1
     * 6 c pre -abeab - 2
     *
     * @param str
     * @param next
     */
    public static void calcNext(String str, int[] next) {
        int len = str.length();

        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < len - 1) {

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

    /**
     * @param text
     * @param pattern
     */
    public static void kmp(String text, String pattern) {
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
                System.out.println("start-end:" + (i - j + 1) + "-" + i);
                System.out.println(text.subSequence(i - j + 1, i + 1));
                j = next[j - 1];
            }

            i++;
        }


    }

}
