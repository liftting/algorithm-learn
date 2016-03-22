/**
 * Created by wm on 16/3/21.
 */
public class PowerTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.isPowerOfTwo(-16));
    }

    public static class Solution {

        /**
         * 转成二进制，判断只能存在一个1，才是符合条件的，
         * 负数不考虑，直接false，不符合
         *
         * @param n
         * @return
         */
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            int cnt = 0;
            while (n != 0) {
                long dig = n % 2;
                if (dig != 0) {
                    cnt++;
                }

                if (cnt > 1) return false;

                n /= 2;
            }

            if (cnt <= 0) return false;

            return true;

        }

    }

}
