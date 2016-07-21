package easy.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/20.
 * 344. Reverse String
 * <p/>
 * Write a function that takes a string as input and returns the string reversed.
 * <p/>
 * Example:
 * Given s = "hello", return "olleh".
 * <p/>
 * 1,反转，使用双指针，来回交换，
 */
public class ReverseString {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.reverseString("hello"));
    }

    public static class Solution {
        public String reverseString(String s) {
            int i = 0, j = s.length() - 1;
            char[] array = new char[s.length()];

            while (i <= j) {
                array[j] = s.charAt(i);
                array[i] = s.charAt(j);
                i++;
                j--;
            }

            return new String(array);
        }
    }

}
