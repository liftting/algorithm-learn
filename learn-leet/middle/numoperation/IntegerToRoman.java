package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/21.
 * 12. Integer to Roman
 * <p/>
 * Given an integer, convert it to a roman numeral.
 * <p/>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p/>
 * 数字转换成为罗马数字，而且限定了数字范围，即不用考虑越界，
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 * <p/>
 * 100 - C
 * <p/>
 * 200 - CC
 * <p/>
 * 300 - CCC
 * <p/>
 * 400 - CD //这里有特殊的处理， 4-5之间
 * <p/>
 * 500 - D
 * <p/>
 * 600 - DC
 * <p/>
 * 700 - DCC
 * <p/>
 * 800 - DCCC
 * <p/>
 * 900 - CM
 * <p/>
 * 1437  MCDXXXVII
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * 解法：
 * 1，暴力遍历即可，
 * 注意数字计算时，一些判断规则，
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.intToRoman(1437));
        for (int i = 100; i <= 900; i += 100) {
            CommonUtil.show(s.intToRoman(i));
        }
    }

    public static class Solution {
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            char roman[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
            int value[] = {1000, 500, 100, 50, 10, 5, 1};

            for (int i = 0; i < value.length; i += 2) { //这里都是1-100进制中进行运算的，
                int c = num / value[i];
                if (c > 0) {
                    if (c < 4) {
                        //该位置有数，
                        for (int j = 1; j <= c; j++) {
                            sb.append(roman[i]);
                        }
                    } else if (c == 4) {
                        //400 CD
                        sb.append(roman[i]).append(roman[i - 1]);

                    } else if (c > 4 && c < 9) {

                        sb.append(roman[i - 1]);//向前取一位，进位
                        for (int j = 6; j <= c; j++) {
                            sb.append(roman[i]);
                        }

                    } else if (c == 9) {
                        // 900 CM
                        sb.append(roman[i]).append(roman[i - 2]);
                    }

                } else {
                    //该位置没有数，不用处理
                }
                num = num % value[i];
            }

            return sb.toString();

        }
    }

}
