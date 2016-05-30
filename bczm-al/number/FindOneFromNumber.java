package number;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/27.
 * <p/>
 * 求 1-n 中 出现的 带有1 的个数， 如 1，2，3，4，5，6，7，8，9，10 = 2
 * <p/>
 * 解法：
 * 包含1 ， 10，100，1000
 * 1，
 */
public class FindOneFromNumber {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.search(15));
    }

    public static class Solution {
        public int calu(int n) {
            int ct = 0;
            while (n != 0) {
                ct += (n % 10 == 1) ? 1 : 0;
                n /= 10;
            }
            return ct;
        }

        public int search(int n) {
            int sum = 0;

            for (int i = 1; i <= n; i++) {
                sum += calu(i);
            }
            return sum;
        }
    }

    public static void search(int n) {

    }

}
