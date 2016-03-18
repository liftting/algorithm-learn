/**
 * Created by wm on 16/3/18.
 * <p/>
 * 263. Ugly Number
 * <p/>
 * 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * <p/>
 * <p/>
 * 基础因子：1 2 3 5 （6 ： 2 3  8 ： 2 2 2   14 ： 2 7）
 * <p/>
 * 2 3 5 6 8 ：
 * <p/>
 * 所谓一个数m是另一个数n的因子，是指n能被m整除，也就是n % m == 0。根据丑数的定义，丑数只能被2、3和5整除。
 * 也就是说如果一个数如果它能被2整除，我们把它连续除以2；如果能被3整除，就连续除以3；如果能被5整除，就除以连续5。
 * 如果最后我们得到的是1，那么这个数就是丑数，否则不是
 * <p/>
 * <p/>
 * 连续除，最后 得1  ，这个连续是指，三个数都要进行尝试处理，
 * <p/>
 * 》》换一个思路：
 * 假设有一个数组存储着已经存在的ugly number，是排好序的，那么我们可以继续计算着这些数据，
 */
public class UglyNumber {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.isUgly(6));
    }

    public static class Solution {
        public boolean isUgly(int num) {

            if (num <= 0) return false;

            while (num % 2 == 0)
                num /= 2;
            while (num % 3 == 0)
                num /= 3;
            while (num % 5 == 0)
                num /= 5;

            return num == 1;
        }

    }

    public static class OtherSolution {

        int[] result;

        public boolean isUgly(int num) {

            //缓存已经计算过的数字
            return false;


        }

        private int getMin(int a, int b, int c) {
            int min = Math.min(a, b);
            return Math.min(min, c);
        }

    }


}
