package middle.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/30.
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],  Return:
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p/>
 * Note:
 * 1.	For the return value, each inner list's elements must follow the lexicographic order.  保持字典中的顺序
 * 2.	All inputs will be in lower-case.  给的数据都是小写的
 * <p/>
 * 解法：
 * 1，key list HashMap,  每次都要对插入的字符串排序，
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showStringList(s.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> map = new HashMap<String, List<String>>();

            for (String s : strs) {
                String tmp = getSortString(s);
                if (map.containsKey(tmp)) {
                    List<String> list = map.get(tmp);
                    list.add(s);
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(s);
                    map.put(tmp, list);
                }
            }

            List<List<String>> result = new ArrayList<List<String>>();

            for (String key : map.keySet()) {
                Collections.sort(map.get(key));
                result.add(map.get(key));
            }

            return result;
        }

        private String getSortString(String s) {
            char[] array = s.toCharArray();
            Arrays.sort(array);

            return new String(array);
        }
    }

}
