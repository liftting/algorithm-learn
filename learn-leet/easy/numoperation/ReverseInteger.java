package easy.numoperation;

/**
 * Created by wm on 16/3/25.
 * <p/>
 * 7. Reverse Integer
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p/>
 * 整数会溢出，
 * 123 - 321
 * <p/>
 * /10
 * <p/>
 * 3
 * 2
 * 1
 */
public class ReverseInteger {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.reverse(1534236469));
    }

    public static class Solution {
        public int reverse(int x) {
            if (x == 0) return x;

            int len = String.valueOf(x).length();
            int data[] = new int[len];
            int n = x;

            int i = 0;
            while (n != 0) {
                data[i++] = n % 10;
                n /= 10;
            }

            long sum = 0;
            int k = 1;
            for (int j = i - 1; j >= 0; j--) {
                sum += data[j] * k;//存在溢出问题，在计算相城时，已经溢出了，得到的数字已经不正确了，
                k *= 10;
                //越界了
                if (sum > Integer.MAX_VALUE) return 0;
            }
            return (int) sum;
        }


        // 网上的，计算方式，
        public static int calute(int x) {
            if (x == 0)
                return 0;
            int sign = 1;
            if (x < 0)
                sign = -1;
            long number = Math.abs((long) x), sum = 0;
            //进行转换逻辑，每次循环都乘10 ，那最后就满足了，
            while (number != 0) {
                sum = sum * 10 + number % 10;
                number = number / 10;
            }
            //正负数，进行判断
            if ((sign == 1 && sum > Integer.MAX_VALUE) || (sign == -1) && (-1 * sum < Integer.MIN_VALUE))
                return 0;
            return sign * (int) sum;
        }


    }

}
