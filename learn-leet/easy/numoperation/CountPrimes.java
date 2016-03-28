package easy.numoperation;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * 204. Count Primes
 * Count the number of prime numbers less than a non-negative number, n.
 * 小于n 的素数，的个数，
 * 素数：质数（prime number）又称素数，有无限个。除了1和它本身以外不再有其他的除数整除
 * <p/>
 * 每一个比1大的整数，要么本身是一个质数，要么可以写成一系列质数的乘积，最小的质数是2。
 * <p/>
 * 思路： 填空法，
 * 以最小的将 大的都按照倍数来进行设置
 */
public class CountPrimes {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.countPrimes(2);
        CommonUtil.showInteger(s.list);
    }

    public static class Solution {
        public List<Integer> list = new ArrayList<Integer>();

        public int countPrimes(int n) {

            boolean[] ct = new boolean[n + 1];
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (ct[i]) continue;
                count++;
                list.add(i);
                //倍数 全部置为false，表示不满足
                for (int j = i; j < n; j += i) {
                    ct[j] = true;
                }
            }
            return count;

        }
    }

}
