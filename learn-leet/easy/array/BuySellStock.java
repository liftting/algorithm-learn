package easy.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/14.
 * 121. Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i
 * <p/>
 * 某天，某石头的价格，  买-卖， 求最大利润
 * //创建表，`
 * <p/>
 * 买一次，然后卖掉，计算最大值，
 * 买 是先 卖的，所以有顺序关系
 * 找一个切分点，使得后半段的最大值减去前半段的最小值这个差最大
 * 最大表， 从后往前填，
 * 最小表 ，从前往后填，
 * <p/>
 * 4 3 2 1 5 8
 */
public class BuySellStock {

    public static void main(String[] args) {

        Solution s = new Solution();
        CommonUtil.show(s.search(new int[]{4, 3, 2, 1, 3}));
        CommonUtil.show(s.maxProfit(new int[]{4, 3, 2, 1, 3}));

    }

    public static class Solution {
        public int maxProfit(int[] prices) {

            if (prices == null || prices.length <= 0) return 0;

            int len = prices.length;
            int[] max = new int[prices.length];
            int[] min = new int[prices.length];
            min[0] = prices[0];

            max[len - 1] = prices[len - 1];

            // 动态规划， 前面一项是否有最大，是根据后面的来计算的，
            for (int i = len - 2; i >= 0; i--) {
                if (prices[i] > max[i + 1]) { // 当前的要比后面的大，那就存起来，
                    max[i] = prices[i];
                } else { // 比起小，说明后面的利润要大，那就存之前的，
                    max[i] = max[i + 1]; // 因为先买， 所以从后来递推，
                }
            }

            int result = 0;
            for (int j = 1; j < len; j++) {
                if (prices[j] < min[j - 1]) {
                    min[j] = prices[j];
                } else {
                    min[j] = min[j - 1];
                }

                int sum = max[j] - min[j]; // 其实这个max[j] 可能就是j - len 之间的最大值，
                if (sum > result) {
                    result = sum;
                }
            }

            return result;

        }

        // dp 动态规划计算，上面的简写
        public int search(int[] prices) {
            int min = prices[0];
            int max = 0;
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(min, prices[i]);
                max = Math.max(max, prices[i] - min);
            }

            return max;

        }

    }
}
