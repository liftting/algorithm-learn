package diffcult.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/30.
 * 30. Substring with Concatenation of All Words
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * <p/>
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * <p/>
 * You should return the indices: [0,9]. // 返回起始点位置
 * <p/>
 * <p/>
 * <p/>
 * 给定一个字符串S和一个字符串数组L，L中的字符串长度都相等，找出S中所有的子串恰好包含L中所有字符各一次，返回子串的起始位置。
 * <p/>
 * 题意：
 * 包含的字符串，必须是完全连接在一起的，中间不包含其他任何字符，
 * <p/>
 * <p/>
 * 解法：
 * 1，定索引位置，i --> 结束位置（length - clen）
 * 然后在这段字符串中，不断的截取 clen长度字符串，不用担心遗漏，因为上级 的循环遍历过来就可以截取到了
 * <p/>
 * 如何校验这段索引点事符合条件的，根据题意：
 */
public class SubStringIndex {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showInteger(s.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    public static class Solution {

        public List<Integer> findSubstring(String s, String[] words) {

            Map<String, Integer> map = new HashMap<String, Integer>();
            for (String st : words) {
                if (map.containsKey(st)) {
                    map.put(st, map.get(st) + 1);
                } else {
                    map.put(st, 1);
                }
            }

            int clen = words[0].length();
            int lenSum = clen * words.length;

            List<Integer> list = new ArrayList<Integer>();

            traverseS:
            for (int i = 0; i <= s.length() - lenSum; i++) {
                Map<String, Integer> dictCopy = new HashMap<String, Integer>(map);

                for (int j = i; j < i + lenSum; j = j + clen) {
                    String subs = s.substring(j, j + clen);
                    Integer num = dictCopy.get(subs);
                    if (num == null || num == 0)
                        continue traverseS;
                    num--;
                    dictCopy.put(subs, num);
                }

                list.add(i);
            }

            return list;


        }

    }

}
