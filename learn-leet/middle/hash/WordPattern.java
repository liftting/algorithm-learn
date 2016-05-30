package middle.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/30.
 * 290. Word Pattern
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 * 1.	pattern = "abba", str = "dog cat cat dog" should return true.
 * 2.	pattern = "abba", str = "dog cat cat fish" should return false.
 * 3.	pattern = "aaaa", str = "dog cat cat dog" should return false.
 * 4.	pattern = "abba", str = "dog dog dog dog" should return false.
 * pa: aaa str = "aa aa aa aa" return false.
 * <p/>
 * Notes: You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 * <p/>
 * 匹配规则，
 * <p/>
 * 解法：
 * 1，HashMap  key:左边的规则， value：右边第一次的值
 */
public class WordPattern {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.wordPattern("abba", "dog cat cat dog"));
        CommonUtil.show(s.wordPattern("abba", "dog cat cat fish"));
        CommonUtil.show(s.wordPattern("jquery", "jquery ni")); // expected -- false
        CommonUtil.show(s.wordPattern("abba", "dog dog dog dog"));
        CommonUtil.show(s.wordPattern("aaa", "dog dog dog dog"));
    }

    public static class Solution {
        public boolean wordPattern(String pattern, String str) {

            HashMap<Character, String> map = new HashMap<Character, String>();

            String[] array = str.split(" ");

            if (pattern.length() != array.length) return false; // 强规则性，必须相等，

            for (int i = 0; i < pattern.length(); i++) {
                Character c = pattern.charAt(i);
                if (i >= array.length) return true;

                String cur = array[i];// 要判断长度是否越界
                if (map.containsKey(c)) {
                    String old = map.get(c);
                    if (!cur.equals(old)) return false;
                } else {
                    map.put(c, cur);
                }
            }

            //再次去重下， abba  -- do do do do

            HashSet<String> set = new HashSet<String>();
            for (Map.Entry<Character, String> entry : map.entrySet()) {
                set.add(entry.getValue());
            }

            if (set.size() < map.size()) return false;


            return true;

        }


    }


}
