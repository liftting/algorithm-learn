/**
 * Created by wm on 16/3/15.
 * <p/>
 * 171. Excel Sheet Column Number
 */
public class ExcelNumOther {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.titleToNumber("AB");
    }

    public static class Solution {
        public int titleToNumber(String s) {

            if (s == null || "".equals(s.trim())) return 0;

            int sum = 0;
            int dig = 1;

            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                sum += dig * getInt(c);
                dig = 26 * dig;
            }

            System.out.print(sum);

            return sum;

        }

        private int getInt(char c) {
            int v = 1;
            System.out.println(v + (c - 'A'));
            return v + (c - 'A');
        }
    }

}
