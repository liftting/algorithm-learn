package easy.string;

import java.util.Arrays;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * <p/>
 * 14. Longest Common Prefix
 * 查找字符串，的最长公共前缀，
 * 暴力遍历，
 * 字符add 逐个递增的
 */
public class LongestCommonPre {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.longestCommonPrefix(new String[]{"abcf", "abde", "abcd"}));
    }

    public static class Solution {
        public String longestCommonPrefix(String[] strs) {

            if (strs == null || strs.length == 0) return "";

            int max = strs[0].length();
            int pos = 0;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() < max) {
                    max = strs[i].length();
                    pos = i;
                }

            }

            String first = strs[pos];
            int j = 0;
            boolean isGo = true;
            while (isGo && j < first.length()) {
                char c = first.charAt(j);
                for (String s : strs) {
                    if (s.charAt(j) != c) {
                        isGo = false;
                        break;
                    }
                }
                if (isGo) {
                    j++;
                }
            }

            return first.substring(0, j);
        }
    }

}
