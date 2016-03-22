package easy.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/22.
 * <p/>
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * <p/>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p/>
 * <p/>
 * 数组中数据+1，后判定是否超过9 ，进行进位
 * <p/>
 * 1 2 3 4 5 6 7 8 9
 * 1 2 3 4 5 6 7 9 0
 */
public class PlusOne {

    public static void main(String[] args) {

        int[] data = new int[]{9, 9, 9, 9};
        CommonUtil.show(data);
        CommonUtil.show(new Solution().plusOne(data));

    }


    public static class Solution {
        public int[] plusOne(int[] digits) {

            if (digits == null || digits.length <= 0) return digits;

            //to there
            int ctDigits[] = new int[digits.length + 1];
            ctDigits[0] = 1;

            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                    //
                    ctDigits[i + 1] = digits[i];
                } else {
                    digits[i] += 1;
                    return digits;
                }
            }

            return ctDigits;


        }
    }

}
