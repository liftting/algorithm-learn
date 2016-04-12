package middle.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/11.
 * 17. Letter Combinations of a Phone Number
 * <p/>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p/>
 * 电话，显示字母
 * 1,map 存储字符，取遍历
 */
public class LeetPhoneNumber {

    public static void main(String[] args) {

        Solution s = new Solution();
        CommonUtil.show(s.letterCombinations("123"));

    }

    public static class Solution {
        public List<String> letterCombinations(String digits) {

            List<String> ret = new ArrayList<String>();
            if (digits == null || digits.length() == 0) return ret;

            //多重循环，递归调用
            List<char[]> list = new ArrayList<char[]>();
            list.add(new char[]{' '});
            list.add(new char[]{});
            list.add(new char[]{'a', 'b', 'c'});
            list.add(new char[]{'d', 'e', 'f'});
            list.add(new char[]{'g', 'h', 'i'});
            list.add(new char[]{'j', 'k', 'l'});
            list.add(new char[]{'m', 'n', 'o'});
            list.add(new char[]{'p', 'q', 'r', 's'});
            list.add(new char[]{'t', 'u', 'v'});
            list.add(new char[]{'w', 'x', 'y', 'z'});

            ret.add("");

            return find(ret, list, digits, 0);

        }

        private List<String> find(List<String> result, List<char[]> data, String digits, int index) {

            if (index == digits.length()) return result;

            int n = digits.charAt(index) - '0';

            if (n > 9) return result;
            char[] array = data.get(n);
            if (array == null || array.length == 0) return result;

            //一级，一级递归计算结果
            List<String> create = new ArrayList<String>();
            for (char c : array) {
                for (String s : result) {
                    create.add(s + c);
                }
            }
            return find(create, data, digits, index + 1);
        }


    }

}
