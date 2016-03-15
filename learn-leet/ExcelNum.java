/**
 * Created by wm on 16/3/14.
 * <p/>
 * 168. Excel Sheet Column Title
 * <p/>
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p/>
 * For example:
 * <p/>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */
public class ExcelNum {

    public static void main(String[] args) {
        Solution s = new Solution();

        s.convertToTitle(1);
        s.convertToTitle(26);
        s.convertToTitle(28);
    }

    public static class Solution {
        /**
         * 26
         *
         * @param n
         * @return
         */
        public String convertToTitle(int n) {
            String s = "";
            while (n != 0) {

                s = getChar(n % 26) + s;

                //26 * 26 * 26倍数的
                if (n % 26 == 0) n = n - 26;

                n = n / 26;

            }

            System.out.println(s);

            return s;
        }

        /**
         * 1 - 26
         *
         * @param pos
         * @return
         */
        private char getChar(int pos) {

            if (pos == 0) return 'Z';

            int start = 'A';
            int t = pos - 1;

            if (pos >= 10 && pos < 20) {
                t = pos - 10;
                start = 'J';
            } else if (pos < 30 && pos >= 20) {
                t = pos - 20;
                start = 'T';
            }

//            System.out.println((char) (start + t));

            return (char) (start + t);
        }
    }

}
