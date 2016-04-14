package middle.string;

import java.util.HashMap;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/13.
 * 3. Longest Substring Without Repeating Characters
 * <p/>
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * <p/>
 * <p/>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 1，暴力，遍历，索引，两个，用于区别后面是否有相同的元素  相邻的不能重复  三个指针， first  pre cur 出来的字串里面没有重复的
 * 这样解不出来， 利用HashMap
 * <p/>
 * 2,hashMap, 遍历时，判断是否存在， 两个first end oldString
 */
public class MaxSubString {

    public static void main(String[] args) {
        Solution s = new Solution();
//        CommonUtil.show(s.lengthOfLongestSubstring("dvdf"));
        s.lengthOfLongestSubstring("cd");
    }

    public static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() <= 0) return 0;

            int first = 0;
            int end = 0;
            String max = "";

            Map<Character, Integer> result = new HashMap<Character, Integer>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (first == end) {
                    result.put(c, first);
                    end++;
                    continue;
                }

                if (!result.containsKey(c)) {
                    result.put(c, i);
                    end++;

                } else {

                    String temp = sub(s, first, end);

                    if (temp.length() > max.length()) {
                        max = temp;
                    }

                    // remove the chars ∈ [slow, newSlow)
                    int newSlow = result.get(c) + 1;
                    for (int k = first; k < newSlow; k++) {
                        result.remove(s.charAt(k));
                    }
                    first = newSlow;

                    result.put(c, i);
                    end++;

                }
            }

            //最后一个不重复时，

            if (end >= s.length()) {
                end = s.length();
                String temp = s.substring(first, end);
                if (temp != null && temp.length() > max.length()) {
                    max = temp;
                }
            }

            return max.length();

        }

        private String sub(String s, int i, int j) {
            int k = j;
            if (k >= s.length()) {
                k = s.length();
            }

            return s.substring(i, k);
        }

    }


}
