package middle.numoperation;

import java.util.HashMap;
import java.util.Map;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/21.
 * 13. Roman to Integer
 * Given a roman numeral, convert it to an integer.
 * <p/>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p/>
 * * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 * <p/>
 * 1437  MCDXXXVII
 * 900   CM
 * <p/>
 * 1000 + 400 + 30 + 7
 */
public class RomanToInteger {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.romanToInt("MCDXXXVII"));
        CommonUtil.show(s.romanToInt("CM"));
    }

    public static class Solution {
        public int romanToInt(String s) {
            if (s == null || s.length() <= 0) return 0;
            char roman[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
            int value[] = {1000, 500, 100, 50, 10, 5, 1};

            int sum = 0;
            Map<Character, Integer> dataMap = new HashMap<Character, Integer>();
            for (int i = 0; i < 7; i++) {
                dataMap.put(roman[i], value[i]);
            }

            int i = 0;
            while (i < s.length()) {
                int y = 0;
                if (i < s.length() - 1) {
                    y = dataMap.get(s.charAt(i + 1));
                }

                int x = dataMap.get(s.charAt(i));
                if (x < y) {
                    sum += y - x;
                    i++;
                    //走两步，
                } else {
                    sum += x;
                }

                i++;
            }

            return sum;

        }
    }

}
