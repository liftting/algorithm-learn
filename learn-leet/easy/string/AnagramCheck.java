package easy.string;

import java.util.Arrays;

/**
 * 242 : Valid Anagram
 * <p/>
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * 排序后，完全相等即是符合要求的，
 */
public class AnagramCheck {

    public static void main(String[] args) {
        Solution s = new Solution();
        boolean is = s.isAnagram("rat", "car");

        System.out.print(is);
    }

    public static class Solution {
        public boolean isAnagram(String s, String t) {

            if (s == null && t == null) return true;

            if (s == null || t == null) return false;

            int ls = s.length();
            int lt = t.length();

            if (ls != lt) return false;

            char[] cs = getSortString(s);
            char[] ct = getSortString(t);

            int i = 0;
            while (i < ls) {
                if (cs[i] == ct[i]) {
                    i++;
                    continue;
                }
                return false;
            }
            return true;

        }

        private char[] getSortString(String s) {
            char[] t = s.toCharArray();
            Arrays.sort(t); //快速排序实现的
            return t;
        }


    }

}
