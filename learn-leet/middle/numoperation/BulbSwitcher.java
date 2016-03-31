package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/31.
 * 319. Bulb Switcher
 * 灯的开关
 * <p/>
 * 初始 全部off
 * 1, 全部  on
 * 2, 打开或关闭 2*n 的 off  toogle ()
 * <p/>
 * 3,  3*n 的off  toogle
 * For the ith round, you toggle every i bulb
 * <p/>
 * For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 * <p/>
 * 当操作到最后一个灯时， 返回多少个灯是on的
 */
public class BulbSwitcher {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.bulbSwitch(10000000));
    }

    public static class Solution {
        // 时间复杂度 O(n * m)
        public int bulbSwitch(int n) {

            if (n < 2) return n;

            boolean[] bulbs = new boolean[n + 1];
            bulbs[1] = true;
            //倍数  划分一半
            for (int swch = 2; swch <= n / 2; swch++) {
                // 以swch 进行递增
                for (int bulb = 2 * swch; bulb < n + 1; bulb += swch) {
                    bulbs[bulb] = !bulbs[bulb];
                }
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (bulbs[i]) cnt++;
            }
            return cnt;
        }

        /**
         * 最简单的处理方式
         * <p/>
         * You first turn on all the bulbs.
         * Then, you turn off every second bulb.(2, 4, 6, ...)
         * On the third round, you toggle every third bulb.(3, 6, 9, ...)
         * For the ith round, you toggle every i bulb.(i, 2i, 3i, ...)
         * For the nth round, you only toggle the last bulb.(n)
         * <p/>
         * If n > 6, you can find that bulb 6 is toggled in round 2 and 3.
         * <p/>
         * Later, it will also be toggled in round 6, and round 6 will be the last round when bulb 6 is toggled.
         * <p/>
         * Here, 2,3 and 6 are all factors of 6 (except 1).
         *
         * @param n
         * @return
         */
        public int simpleSwitch(int n) {
            return (int) Math.sqrt(n);
        }
    }

}
